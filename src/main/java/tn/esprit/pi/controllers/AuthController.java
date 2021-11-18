package tn.esprit.pi.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pi.security.CustomLoginSuccessHandler;
import tn.esprit.pi.security.jwt.JwtUtils;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.payload.request.LoginRequest;
import tn.esprit.pi.payload.response.JwtResponse;
import tn.esprit.pi.payload.response.MessageResponse;
import tn.esprit.pi.security.services.UserDetailsImpl;
import tn.esprit.pi.services.IUserservice;
import tn.esprit.pi.services.UserService;
import tn.esprit.pi.services.EmailService;
import tn.esprit.pi.services.IRoleservice;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/User/Access")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	CustomLoginSuccessHandler customLoginSuccessHandler;
	@Autowired
	IUserservice iuserservice;

	@Autowired
	IRoleservice iroleservice;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	private EmailService emailService;

	@PostMapping("/login")
	public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		String resultat = customLoginSuccessHandler.onAuthenticationSuccess(loginRequest.getUsername(),
				loginRequest.getPassword());
		if (resultat.length() ==0) {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtils.generateJwtToken(authentication);

			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			return new JwtResponse(jwt, userDetails.getUsername(), roles.get(0).toString());
		}
		return new JwtResponse(resultat, "", "");
	}

	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
		if (user == null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add values!"));
		}
		if (user.getAddress().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add address!"));
		}
		if (user.getAddress().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add bithday date!"));
		}
		if (!(user.getBirthdate() instanceof Date)) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add bithday date!"));
		}
		if (user.getFirstName().equals("")) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please add your first name!"));
		}
		if (user.getMail().equals("") || !UserService.validate(user.getMail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: please check your mail!"));
		}
		if (iuserservice.findUserBylogin(user.getLogin()) != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		// Create new user's account
		user.setPassword(encoder.encode(user.getPassword()));
		if (user.getRole() == null) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Could you please add a role for the new user!"));
		}
		user.setValid(true);
		user.setAccountNonLocked(true);
		user.setFailedAttempt(0);
		
		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setTo(user.getMail());
		passwordResetEmail.setSubject("Registration");
		passwordResetEmail.setText(
				"Dear " + user.getFirstName() + ":\n" + "Félicitation !" 
						+ ":\n" 
						+ "Your role is " + user.getRole().getRoleType().name()
						+ ".\n"
						+"Team Kindo Garden") ;

		emailService.sendEmail(passwordResetEmail);
		iuserservice.createUser(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/forgot/{login}")
	public String processForgotPasswordForm(@PathVariable("login") String login,
			HttpServletRequest request) throws Exception {
		User user = iuserservice.findUserBylogin(login);

		if (user == null) {
			return "user not found";
		} else {
			// Generate random 36-character string token for reset password
			user.setResettoken(UUID.randomUUID().toString());

			// Save token to database
			iuserservice.updateUser(user);

			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setTo(user.getMail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below: \n "
					+ "https://localhost:44330/User/reset/"
					+ "\n "
					+ "Please fill your reset token and your new password \n Your token is:"
					+ user.getResettoken());

			emailService.sendEmail(passwordResetEmail);
		}
		return "mail sent";

	}

	// Process reset password form
	@PostMapping("/reset/{token}/{newpassword}")
	public String setNewPassword(@PathVariable("token") String token,@PathVariable("newpassword") String newpassword ) throws Exception {
		User user = iuserservice.findUserByResetToken(token);
		if (user != null) {
			user.setPassword(encoder.encode(newpassword));
			user.setResettoken(null);
			iuserservice.updateUser(user);
			return "passwored reseted";

		} else {
			return "operation regected";
		}
	}
}
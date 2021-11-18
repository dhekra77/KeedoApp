package tn.esprit.pi.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.pi.config.FileUploadUtil;
import tn.esprit.pi.entities.Message;
import tn.esprit.pi.entities.Retour;
import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.MessageService;
//import static tn.esprit.pi.controllers.AuthController.CURRENTUSER;

@RestController
@RequestMapping("message/")
public class MessageController {

	ObjectMapper objM = new ObjectMapper();

	@Autowired
	MessageService messageService;

	// localhost:8080/SpringMVC/servlet/message/connect
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PutMapping("connect")
	public Retour<User> connected() throws Exception {
		return messageService.connected();
	}

	// localhost:8080/SpringMVC/servlet/message/disConnect
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PutMapping("disConnect")
	public Retour<User> disConnected() throws Exception {
		return messageService.disConnected();

	}

	// localhost:8080/SpringMVC/servlet/message/sendMessage/4
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PostMapping(value = "sendMessage/{idR}")
	public Retour<Message> sendMessage(@PathVariable("idR") int idR, @RequestBody Message message) throws Exception {

		return messageService.sendMessage(idR, message);
	}

	// localhost:8080/SpringMVC/servlet/message/sendFile/4
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@PostMapping(value = "sendFile/{idR}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public Retour<Message> sendFile(@PathVariable("idR") int idR, @RequestPart("image") MultipartFile file)
			throws Exception {

		Message message = new Message();
		String fileNameRepo = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "uploadYass/";
		System.out.println("image name =" + fileNameRepo);
		try {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/uploadYass/")
					.path(fileNameRepo).toUriString();
			System.out.println("file url =====>" + fileDownloadUri);
			message.setImage(fileDownloadUri.getBytes());
			FileUploadUtil.saveFile(uploadDir, fileNameRepo, file);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return messageService.sendMessage(idR, message);
	}

	// localhost:8080/SpringMVC/servlet/message/checkMsg/3/4
	//@PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@GetMapping("checkMsg/{idR}")
	public List<Message> checkMessage(@PathVariable("idR") int idR) throws Exception {
		return messageService.checkMessage(idR);
	}
	
	// localhost:8080/SpringMVC/servlet/message/followers/3
	// @PreAuthorize("hasAuthority('KindergardenDirector') or hasAuthority('DaycareManager') or hasAuthority('Doctor') or hasAuthority('Parent')")
	@GetMapping("followers/{idS}")
	public List<User> followers(@PathVariable("idS")  int id) {
		return messageService.followers(id);
	}
	
	@GetMapping("receiver/{idR}")
	public User getReceiver(@PathVariable("idR")  int id) {
		return messageService.getReceiver(id);
	}
}

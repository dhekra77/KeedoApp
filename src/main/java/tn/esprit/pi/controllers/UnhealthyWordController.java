package tn.esprit.pi.controllers;

import tn.esprit.pi.services.UnhealthyWordsServiceImpl;
import tn.esprit.pi.entities.UnhealthyWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UnhealthyWordController {
	@Autowired  
    UnhealthyWordsServiceImpl unhealthyWordService;

    
	//URL: http://localhost:9293/SpringMVC/servlet/UnhealthyWords/add
	@PreAuthorize("hasAuthority('Admin')")	
		@PostMapping("UnhealthyWords/add")
		public String addWord(@RequestBody UnhealthyWord word) {
			if (unhealthyWordService.wordExists(word.getWord())){
				return ("this word is already added");
			}
			unhealthyWordService.addWord(word);
			return ("added successfully");   
		}

	
    //URL: http://localhost:9293/SpringMVC/servlet/UnhealthyWords/delete/?word=
	@PreAuthorize("hasAuthority('Admin')")	
		@DeleteMapping("UnhealthyWords/delete")
		public String deleteUnhealthyWord(@RequestParam("word")String word) {
			unhealthyWordService.deleteWord(word);
			return ("deleted successfully");
    }

	
    //URL: http://localhost:9293/SpringMVC/servlet/UnhealthyWords/get-all-unhealthy
	@PreAuthorize("hasAuthority('Admin')")	
		@GetMapping("UnhealthyWords/get-all-unhealthy")
    	public List<UnhealthyWord> unhealthyWordList() {
        	return (unhealthyWordService.getUnhealthyWordList());
    	}
}
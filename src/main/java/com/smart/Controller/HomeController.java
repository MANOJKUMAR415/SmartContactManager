package com.smart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smart.Entity.User;
import com.smart.dao.UserRepository;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	@ResponseBody
	public String test() {
		
		User user=new User();
		user.setName("manoj");
		user.setEmail("pmanojmanu01@gmail.com");
		
		userRepo.save(user);
		return "working";
		
	}
	
	
	
}

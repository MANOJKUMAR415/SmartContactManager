package com.smart.Controller;

import javax.servlet.http.HttpSession;

import org.aspectj.bridge.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smart.Entity.User;
import com.smart.dao.UserRepository;
import com.smart.helper.MessageHelper;







@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/")
	public String home(Model modal ) {
		modal.addAttribute("title","home page - SmartContactManager");
	    return "home";
		}
	
	
	
	@RequestMapping("/about")
	public String about(Model modal ) {
		modal.addAttribute("title","about page - SmartContactManager");
		
		return "about";
		}
	
	
	@RequestMapping("/signup")
	public String signup(Model model ) {
		
		model.addAttribute("title","Register");
		model.addAttribute("user",new User());
		return "signup";
	}
	
	// to save user
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public String Register(@ModelAttribute("user") User user ,@RequestParam(value = "agreement", 
	                          defaultValue = "false")
	                          boolean agreement,Model model,HttpSession session) {
		
		try {
		if(!agreement) {
			System.out.println("Accept terms ang conditions");
			throw new Exception("you have not agreed the terms and conditions");
	}
		user.setRole("Role-user");
		user.setEnabled(true);
		
		user.setImageUrl("default.png");
		User result=this.userRepo.save(user);
		model.addAttribute("user", new User());
		session.setAttribute("message", new MessageHelper("Successfuly Register", "alert-success"));
		return "signup";
		
		}catch (Exception e) {
			e.printStackTrace();
           model.addAttribute("user",user);
           session.setAttribute("message", new MessageHelper("something went wrong"+e.getMessage(),"alert-error"));
           return "signup";
		}
		 
		
	}
	
	
	@RequestMapping("/login")
	public String homepage(Model modal ) {
		modal.addAttribute("title","home page - SmartContactManager");
	    return "login";
		}
	
	
	
	
	
}

package it.cefi.myApts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import it.cefi.myApts.domini.User;
import it.cefi.myApts.service.MeetingsService;
import it.cefi.myApts.service.RemindersService;
import it.cefi.myApts.service.UserService;
@Controller
public class HomeController {
	@Autowired
	UserService userService;
	@Autowired
	MeetingsService meetingsService;
	@Autowired
	RemindersService remindersService;
	
	@GetMapping("/")
	public String home(Model model) {
		meetingsService.checkAge();
		
		String username = userService.nameUsername();

		model.addAttribute("new1", meetingsService.countOldAndNew(username, true));
		model.addAttribute("old1", meetingsService.countOldAndNew(username, false));
		model.addAttribute("rem", remindersService.countReminders(username));
		return "index";
	}
	
	@GetMapping("/insert")
	public String insert()
	{
		return "insert";
	}
	
	@GetMapping("/insert-rem")
	public String insert_rem()
	{	
		return "insert-rem";		
	}
	
	@GetMapping("/signin")
	public String formRegistra()
	{
		return "signin";
	}
	
	@PostMapping("/signin")
	public String submitRegistra(@ModelAttribute User user)
	{
		userService.saveUser(user);
		return "login";
	}
	
	
	
}

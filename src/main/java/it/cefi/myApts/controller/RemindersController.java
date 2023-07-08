package it.cefi.myApts.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.cefi.myApts.domini.Reminders;
import it.cefi.myApts.repository.UserRepository;
import it.cefi.myApts.service.RemindersService;
import it.cefi.myApts.service.UserService;
//stop

@Controller
public class RemindersController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RemindersService remindersService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/reminders")
	public String viewReminders(Model model) {		
		model.addAttribute("rem", remindersService.findthereminders(userService.nameUsername()));
		return "view-reminders";
	}

	@PostMapping("/insert-view-rem")
	public String saveAndViewReminders(@ModelAttribute Reminders remy) {
		remy.setUser(userRepository.findById(userService.nameUsername()).get());
		remindersService.savethereminder(remy);
		return "redirect:/reminders";

	}

	@PostMapping("/insert-rem")
	public String saveReminders(@ModelAttribute Reminders remy) {		
		remy.setUser(userRepository.findById(userService.nameUsername()).get());
		
		remindersService.savethereminder(remy);
		return "redirect:/insert-rem";

	}

	@Transactional
	@PostMapping("/delete")
	public String deleteReminders(@RequestParam(value = "present", required = false) List<Long> values) {
		if (values != null)

			remindersService.deletethereminders(values);

		return "redirect:/reminders";

	}

}

package it.cefi.myApts.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.cefi.myApts.domini.Meetings;
import it.cefi.myApts.domini.User;
import it.cefi.myApts.repository.UserRepository;
import it.cefi.myApts.service.MeetingsService;
import it.cefi.myApts.service.UserService;



@Controller
public class MeetingsController {

	@Autowired
	MeetingsService meetingService;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;


	@GetMapping("/view")
	public String view(Model model) {
		meetingService.checkAge();
		model.addAttribute("time", "new");	
		model.addAttribute("meeting", meetingService.orderNewMeeting(userService.nameUsername()));

		return "view";
	}

	@GetMapping("/view/old")
	public String viewOld(Model model) {

		meetingService.checkAge();
		model.addAttribute("time", "old");
		model.addAttribute("meeting", meetingService.orderOldMeeting(userService.nameUsername()));
		return "view";
	}

	@PostMapping("/save-view")
	public String saveAndViewMeetings(@ModelAttribute Meetings date) {
		
		Meetings meeting = insertMeeting(date);
		
		meetingService.savethemeetings(insertMeeting(meeting));

		if (meeting.isNewmeeting())

			return "redirect:/view";

		else

			return "redirect:/view/old";
	}

	@PostMapping("/save")
	public String saveMeetings(@ModelAttribute Meetings date) {
		
		meetingService.savethemeetings(insertMeeting(date));

		return "redirect:/insert";
	}

	@Transactional
	@PostMapping("/cancel")
	public String cancelMeeting(@RequestParam(value = "present", required = false) List<Long> values,
			HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		if (values == null) {

			return "redirect:" + referer;
		}

		meetingService.deletethemeetings(values);

		return "redirect:" + referer;
	}

	public Meetings insertMeeting(Meetings meeting)
	{
		String datetime = meeting.getDateAndTime();

		String[] lista = datetime.split(" ");

		DateTimeFormatter formatta = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		LocalDate data = LocalDate.parse(lista[0], formatta);

		meeting.setDate(data);

		String[] ora = lista[1].split(":");
		int hour = Integer.valueOf(ora[0]);

		if (lista[2].equals("PM")) {
			if(hour == 12)
				hour = 12;
			else
			hour = hour + 12;
		}else {
			if(hour == 12)
				hour = 0;
		}
		
		LocalTime time = LocalTime.of(hour, Integer.valueOf(ora[1]));

		meeting.setTime(time);

		int giorni = (int) ChronoUnit.DAYS.between(LocalDate.now(), data);

		if (giorni < 0) {
			meeting.setNewmeeting(false);
		} else {
			meeting.setNewmeeting(true);
		}
		//date.setUser(userRepository.findById(userService.nameUsername()).get());	
		User user = userService.findUserClass(userService.nameUsername());
	
		meeting.setUser(user);
		
		return meeting;
	}
	
}

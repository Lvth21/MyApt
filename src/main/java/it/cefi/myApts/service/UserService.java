package it.cefi.myApts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import it.cefi.myApts.domini.User;
import it.cefi.myApts.repository.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User ut)
	{
		userRepository.save(ut);
	}
	
	public User findUserClass(String ut)
	{
		return userRepository.findByUsername(ut);
	}
	
	public String nameUsername() {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}
	
}

package it.cefi.myApts.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.cefi.myApts.domini.User;
import it.cefi.myApts.repository.UserRepository;


@Service
public class ConfigureUser implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		String pwd = new BCryptPasswordEncoder().encode(user.getPwd());
		
		return new org.springframework.security.core.userdetails.User(username,
				pwd, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
	}

}

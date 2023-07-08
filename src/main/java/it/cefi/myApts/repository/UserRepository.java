package it.cefi.myApts.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import it.cefi.myApts.domini.User;
@PreAuthorize("hasRole('USER')")
public interface UserRepository extends CrudRepository<User, String> {
	
	public User findByUsername(String name);
	
}

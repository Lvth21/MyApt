package it.cefi.myApts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import it.cefi.myApts.domini.Reminders;
@PreAuthorize("hasRole('USER')")
public interface RemindersRepository extends CrudRepository<Reminders, Long> {
	
	public long deleteByIdreminders(Long long1);
	
	@Query(value = "SELECT * FROM account_security.reminders WHERE username = :username", nativeQuery = true)
	List<Reminders> findRemindersByUser(@Param("username") String username);
	
	@Query(value = "SELECT COUNT(*) FROM account_security.reminders WHERE username = :username", nativeQuery = true)
	long countRemindersByUsername(@Param("username") String username);
}

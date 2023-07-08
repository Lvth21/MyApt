package it.cefi.myApts.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import it.cefi.myApts.domini.Meetings;

@PreAuthorize("hasRole('USER')")
public interface MeetingsRepository extends CrudRepository<Meetings, Long>{

	public List<Meetings> findByNewmeeting(boolean bool);
	
	public long deleteByIdmeetings(Long long1);

	public Meetings findByIdmeetings(long id);
	
	@Query(value = "SELECT COUNT(*) FROM account_security.meetings WHERE username = :username AND newmeeting = :id", nativeQuery = true)
	long countNewOld(@Param("username") String username, @Param("id") boolean bool);

	@Query(value = "SELECT * FROM account_security.meetings WHERE username = :username AND newmeeting = 0 ORDER BY DATE(date) DESC, TIME(time) DESC", nativeQuery = true)
	List<Meetings> findUpcomingOldMeetings(@Param("username") String username);

	@Query(value = "SELECT * FROM account_security.meetings WHERE username = :username AND newmeeting = 1 ORDER BY DATE(date) ASC, TIME(time) ASC", nativeQuery = true)
	List<Meetings> findUpcomingNewMeetings(@Param("username") String username);
}

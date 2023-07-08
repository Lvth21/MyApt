package it.cefi.myApts.domini;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "account")
public class User {
	@Id
	@Column(length = 40)
	private String username;
	
	@Column(length = 40, nullable = false)
	private String firstname;
	
	@Column(length = 40, nullable = false)
	private String lastname;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 30, nullable = false)
	private String pwd;
	
	@OneToMany(mappedBy = "idmeetings")
	private Set<Meetings> meetings;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "idreminders")
	private Set<Reminders> reminders;

	@Column(length = 30, nullable = false)
	private String role;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Meetings> getMeetings() {
		return meetings;
	}

	public void setMeetings(Set<Meetings> meetings) {
		this.meetings = meetings;
	}

	public Set<Reminders> getReminders() {
		return reminders;
	}

	public void setReminders(Set<Reminders> reminders) {
		this.reminders = reminders;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	

}

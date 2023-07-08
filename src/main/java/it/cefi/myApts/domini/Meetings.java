package it.cefi.myApts.domini;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "meetings")
public class Meetings {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long idmeetings;

	@Column(nullable = false)
	private LocalDate date;

	@Column(nullable = false)
	private LocalTime time;
	
	@Column(length = 40, nullable = false)
	private String topic;

	@Column(length = 70)
	private String con;

	@Column(length = 500)
	private String reminder;

	@Column
	private boolean newmeeting;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;

	@Autowired
	private String dateAndTime;

	public String getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public long getIdmeetings() {
		return idmeetings;
	}

	public void setIdmeetings(long idmeetings) {
		this.idmeetings = idmeetings;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public boolean isNewmeeting() {
		return newmeeting;
	}

	public void setNewmeeting(boolean newmeeting) {
		this.newmeeting = newmeeting;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}

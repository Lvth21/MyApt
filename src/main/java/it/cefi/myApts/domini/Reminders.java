package it.cefi.myApts.domini;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reminders")
public class Reminders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long idreminders;
	
	@Column(length = 700, nullable = false)
	private String remind;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	

	public long getIdreminders() {
		return idreminders;
	}

	public void setIdreminders(long idreminders) {
		this.idreminders = idreminders;
	}

	public String getRemind() {
		return remind;
	}

	public void setRemind(String remind) {
		this.remind = remind;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	
}


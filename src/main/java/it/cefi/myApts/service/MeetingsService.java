package it.cefi.myApts.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cefi.myApts.domini.Meetings;
import it.cefi.myApts.repository.MeetingsRepository;



@Service
public class MeetingsService {
	@Autowired
	MeetingsRepository meetingRepository;


	public void savethemeetings(Meetings meeting) {
		meetingRepository.save(meeting);
	}

	public List<Meetings> newMeetings(boolean bool) {
		if (bool)
			return meetingRepository.findByNewmeeting(true);
		else
			return meetingRepository.findByNewmeeting(false);
	}

	public void deletethemeetings(List<Long> values) {
		for (long long1 : values) {
			meetingRepository.deleteByIdmeetings(long1);
		}

	}

	public void checkAge() {
		List<Meetings> meeting = newMeetings(true);

		for (Meetings meet : meeting) {
			
			if ((int) ChronoUnit.DAYS.between(LocalDate.now(), meet.getDate()) < 0) {
				
				meet.setNewmeeting(false);
				
				savethemeetings(meet);
			}
		}
	}

	public Meetings findMeetings(long id) {
		return meetingRepository.findByIdmeetings(id);
	}

	public List<Meetings> orderOldMeeting(String username) {
		return meetingRepository.findUpcomingOldMeetings(username);
	}

	public List<Meetings> orderNewMeeting(String username) {
		return meetingRepository.findUpcomingNewMeetings(username);
	}
	
	public long countOldAndNew(String username, boolean newOrOld){
		
		return meetingRepository.countNewOld(username, newOrOld);
	}
	

}

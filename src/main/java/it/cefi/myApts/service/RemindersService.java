package it.cefi.myApts.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.cefi.myApts.domini.Reminders;
import it.cefi.myApts.repository.RemindersRepository;


@Service
public class RemindersService {
	@Autowired
	private RemindersRepository remindersRepository;
	
	public List<Reminders> findthereminders(String username)
	{
		return remindersRepository.findRemindersByUser(username);
	}
	
	public void savethereminder(Reminders reminder)
	{
		remindersRepository.save(reminder);
	}
	
	public void deletethereminders(List<Long> values)
	{
		for (long long1 : values) {
			remindersRepository.deleteByIdreminders(long1);
		}
		
	}
	
	public long countReminders(String username) 
	{
		return remindersRepository.countRemindersByUsername(username);
	}
}

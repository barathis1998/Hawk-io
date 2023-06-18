package com.hawkio.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hawkio.api.model.Subscriber;
import com.hawkio.api.repository.SubscriberRepository;

@Service
public class SubscriberService {
	
	@Autowired
	private SubscriberRepository repository;
	
	public void saveSubscriber(Subscriber subscriber) {
		repository.save(subscriber);
	}
	
	public boolean isEmailExists(String email) {
        Subscriber subscriber = repository.findByEmailId(email);
        return subscriber != null;
	}
}

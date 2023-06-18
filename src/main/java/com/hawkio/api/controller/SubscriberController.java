package com.hawkio.api.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hawkio.api.model.Subscriber;
import com.hawkio.api.service.MapValidationErrorService;
import com.hawkio.api.service.SubscriberService;
import com.hawkio.api.validator.SubscriberValidator;



@RestController
@CrossOrigin(origins = " * ", allowedHeaders = " * ")
@RequestMapping(value = "/api/v1")
public class SubscriberController {
	
//	@Autowired
//	private SubscriberRepository repository;
	
	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
	private SubscriberValidator emailValidator;
	
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("/addSubscriber")
	public ResponseEntity<?> saveSubscriber(@Valid @RequestBody Subscriber subscriber, BindingResult result) {
		
		emailValidator.validate(subscriber, result);
	    ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(result);
	    if (errorMap != null) {
	        return errorMap;
	    }

	    boolean emailExists = subscriberService.isEmailExists(subscriber.getEmailId());
	    if (emailExists) {
	        return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
	    } else {
	        subscriberService.saveSubscriber(subscriber);
	        return new ResponseEntity<>("Email Added Successfully", HttpStatus.CREATED);
	    }
	}

}

package com.hawkio.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hawkio.api.model.Subscriber;

@Component
public class SubscriberValidator implements Validator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    @Override
    public boolean supports(Class<?> clazz) {
        return Subscriber.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "field.required", "Email is required");
        
        Subscriber subscriber = (Subscriber) target;
        if (!subscriber.getEmailId().matches(EMAIL_REGEX)) {
            errors.rejectValue("emailId", "field.pattern", "Invalid email format");
        }
    }
}

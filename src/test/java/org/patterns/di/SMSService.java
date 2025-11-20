package org.patterns.di;

import org.springframework.stereotype.Service;

/**
 * Another implementation of the MessageService interface that provides SMS messages.
 * This class is also annotated with @Service to mark it as a Spring service component.
 * Spring can inject either this implementation or EmailService based on configuration.
 */
@Service("smsService")
public class SMSService implements MessageService {

    @Override
    public String getMessage() {
        return "SMS message: Hello from Spring DI!";
    }
}

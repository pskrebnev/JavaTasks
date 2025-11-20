package org.patterns.di;

import org.springframework.stereotype.Service;

/**
 * An implementation of the MessageService interface that provides email messages.
 * This class is annotated with @Service to mark it as a Spring service component
 * that can be automatically detected and registered as a bean.
 */
@Service("emailService")
public class EmailService implements MessageService {

    @Override
    public String getMessage() {
        return "Email message: Hello from Spring DI!";
    }
}

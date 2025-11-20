package org.patterns.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A client class that uses MessageService through dependency injection.
 * This class demonstrates both constructor injection (preferred) and field injection.
 */
@Component
public class MessageClient {
    
    // Constructor injection (preferred approach)
    private final MessageService messageService;
    
    // Field injection (alternative approach)
    @Autowired
    @Qualifier("smsService")  // Specifies which implementation to inject
    private MessageService smsService;
    
    /**
     * Constructor with dependency injection.
     * Spring will automatically inject an implementation of MessageService.
     * By default, Spring will inject EmailService since it's the primary bean.
     * 
     * @param messageService The message service to be injected
     */
    @Autowired
    public MessageClient(@Qualifier("emailService") MessageService messageService) {
        this.messageService = messageService;
    }
    
    /**
     * Sends a message using the injected email service.
     * 
     * @return The message from the email service
     */
    public String sendEmail() {
        return messageService.getMessage();
    }
    
    /**
     * Sends a message using the injected SMS service.
     * 
     * @return The message from the SMS service
     */
    public String sendSMS() {
        return smsService.getMessage();
    }
}
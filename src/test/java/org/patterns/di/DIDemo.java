package org.patterns.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * A demo class that demonstrates Spring's dependency injection in action.
 * This class creates a Spring application context, retrieves the MessageClient bean,
 * and calls its methods to show the injected dependencies.
 */
public class DIDemo {
    
    public static void main(String[] args) {
        // Create a Spring application context using our configuration class
        try (AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class)) {
            
            System.out.println("Spring context initialized successfully!");
            
            // Get the MessageClient bean from the context
            MessageClient client = context.getBean(MessageClient.class);
            
            // Use the client to send messages using both injected services
            System.out.println("Using constructor-injected EmailService: " + client.sendEmail());
            System.out.println("Using field-injected SMSService: " + client.sendSMS());
            
            // We can also directly get the service beans from the context
            MessageService emailService = context.getBean("emailService", MessageService.class);
            MessageService smsService = context.getBean("smsService", MessageService.class);
            
            System.out.println("Directly using EmailService: " + emailService.getMessage());
            System.out.println("Directly using SMSService: " + smsService.getMessage());
            
            // List all beans in the context
            System.out.println("\nAll beans in the context:");
            for (String beanName : context.getBeanDefinitionNames()) {
                System.out.println(beanName);
            }
        }
    }
}
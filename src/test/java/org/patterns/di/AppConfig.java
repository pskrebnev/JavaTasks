package org.patterns.di;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class that defines the application context.
 * This class is annotated with @Configuration to mark it as a source of bean definitions.
 * The @ComponentScan annotation tells Spring to scan the specified package for components.
 */
@Configuration
@ComponentScan("org.patterns.di")
public class AppConfig {
    
    // We don't need to explicitly define beans here because we're using component scanning
    // and stereotype annotations (@Service, @Component) on our classes.
    
    // If we wanted to manually define beans, we could do so with @Bean methods:
    /*
    @Bean
    public MessageService emailService() {
        return new EmailService();
    }
    
    @Bean
    public MessageService smsService() {
        return new SMSService();
    }
    
    @Bean
    public MessageClient messageClient() {
        return new MessageClient(emailService());
    }
    */
}
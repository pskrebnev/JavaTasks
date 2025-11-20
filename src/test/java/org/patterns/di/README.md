# Spring Dependency Injection Example

This package demonstrates a simple example of Dependency Injection using Spring Framework.

## What is Dependency Injection?

Dependency Injection (DI) is a design pattern that implements Inversion of Control (IoC) for resolving dependencies. In the pattern, the client delegates the responsibility of providing its dependencies to external code (the injector).

Spring Framework provides a comprehensive infrastructure support for developing applications with DI.

## Components in this Example

1. **MessageService (Interface)**: Defines the contract for message services.
2. **EmailService**: An implementation of MessageService that provides email messages.
3. **SMSService**: Another implementation of MessageService that provides SMS messages.
4. **MessageClient**: A client class that uses MessageService through dependency injection.
5. **AppConfig**: Spring configuration class that defines the application context.
6. **DIDemo**: A demo class that demonstrates Spring's dependency injection in action.

## Types of Dependency Injection Demonstrated

1. **Constructor Injection** (preferred approach):
   ```java
   @Autowired
   public MessageClient(@Qualifier("emailService") MessageService messageService) {
       this.messageService = messageService;
   }
   ```

2. **Field Injection**:
   ```java
   @Autowired
   @Qualifier("smsService")
   private MessageService smsService;
   ```

## How to Run the Example

Execute the `main` method in the `DIDemo` class. The output will show:
- The Spring context initialization
- Messages from both injected services through the client
- Messages directly from the service beans
- A list of all beans in the context

## Key Spring Annotations Used

- **@Service**: Marks a class as a service component
- **@Component**: Marks a class as a Spring component
- **@Autowired**: Marks a constructor, field, or method for dependency injection
- **@Qualifier**: Specifies which implementation to inject when multiple are available
- **@Configuration**: Marks a class as a source of bean definitions
- **@ComponentScan**: Tells Spring to scan the specified package for components

## Benefits of Dependency Injection

1. **Reduced coupling**: Classes are not responsible for creating their dependencies
2. **Increased testability**: Dependencies can be mocked for unit testing
3. **Flexibility**: Implementations can be swapped without changing client code
4. **Centralized configuration**: Dependencies are managed in a central location
5. **Lifecycle management**: Spring manages the lifecycle of beans
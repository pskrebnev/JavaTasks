package org.patterns.di;

/**
 * A simple service interface that will be injected into client classes.
 * This demonstrates the dependency inversion principle where high-level modules
 * depend on abstractions rather than concrete implementations.
 */
public interface MessageService {
    /**
     * Gets a message from the service.
     * 
     * @return A string message
     */
    String getMessage();
}
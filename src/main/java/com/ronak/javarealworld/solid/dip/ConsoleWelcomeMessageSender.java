package com.ronak.javarealworld.solid.dip;

public final class ConsoleWelcomeMessageSender implements WelcomeMessageSender {
    @Override
    public void sendTo(Customer customer) {
        System.out.println("Welcome message queued for " + customer.email());
    }
}

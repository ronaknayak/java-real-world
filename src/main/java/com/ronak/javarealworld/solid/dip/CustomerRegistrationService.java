package com.ronak.javarealworld.solid.dip;

public final class CustomerRegistrationService {
    private final CustomerRepository customerRepository;
    private final WelcomeMessageSender welcomeMessageSender;

    public CustomerRegistrationService(CustomerRepository customerRepository, WelcomeMessageSender welcomeMessageSender) {
        this.customerRepository = customerRepository;
        this.welcomeMessageSender = welcomeMessageSender;
    }

    public void register(Customer customer) {
        customerRepository.save(customer);
        welcomeMessageSender.sendTo(customer);
    }
}

package com.ronak.javarealworld.solid.dip;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var registrationService = new CustomerRegistrationService(
                new InMemoryCustomerRepository(), new ConsoleWelcomeMessageSender());
        registrationService.register(new Customer("CUS-291", "buyer@acme.example"));
    }
}

package com.ronak.javarealworld.solid.violations.dip;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        new CustomerRegistrationService().register("CUS-291", "buyer@acme.example");
        System.out.println("Violation: registration policy cannot change infrastructure without being edited.");
    }
}

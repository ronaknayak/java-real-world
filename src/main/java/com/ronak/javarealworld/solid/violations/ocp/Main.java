package com.ronak.javarealworld.solid.violations.ocp;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        System.out.println("Shipping quote: " + new ShippingQuoteCalculator().calculate("US", true));
        System.out.println("Violation: a new carrier, country, or rule changes the calculator.");
    }
}

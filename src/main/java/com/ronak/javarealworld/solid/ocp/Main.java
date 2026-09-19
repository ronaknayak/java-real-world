package com.ronak.javarealworld.solid.ocp;

import java.math.BigDecimal;
import java.util.List;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var quoteService = new ShippingQuoteService(List.of(
                new DomesticShippingRatePolicy(), new InternationalShippingRatePolicy()));
        var shipment = new Shipment("DE", new BigDecimal("5000.00"), true);
        System.out.println("Shipping quote: " + quoteService.quoteFor(shipment));
    }
}

package com.ronak.javarealworld.solid.ocp;

import java.math.BigDecimal;

public final class DomesticShippingRatePolicy implements ShippingRatePolicy {
    @Override
    public boolean supports(Shipment shipment) {
        return "IN".equalsIgnoreCase(shipment.destinationCountry());
    }

    @Override
    public BigDecimal calculate(Shipment shipment) {
        return shipment.express() ? new BigDecimal("250.00") : new BigDecimal("100.00");
    }
}

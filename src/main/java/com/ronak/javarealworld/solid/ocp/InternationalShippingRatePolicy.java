package com.ronak.javarealworld.solid.ocp;

import java.math.BigDecimal;

public final class InternationalShippingRatePolicy implements ShippingRatePolicy {
    @Override
    public boolean supports(Shipment shipment) {
        return !"IN".equalsIgnoreCase(shipment.destinationCountry());
    }

    @Override
    public BigDecimal calculate(Shipment shipment) {
        BigDecimal base = shipment.express() ? new BigDecimal("1800.00") : new BigDecimal("900.00");
        return base.add(shipment.declaredValue().multiply(new BigDecimal("0.02")));
    }
}

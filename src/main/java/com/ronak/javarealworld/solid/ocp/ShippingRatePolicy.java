package com.ronak.javarealworld.solid.ocp;

import java.math.BigDecimal;

public interface ShippingRatePolicy {
    boolean supports(Shipment shipment);

    BigDecimal calculate(Shipment shipment);
}

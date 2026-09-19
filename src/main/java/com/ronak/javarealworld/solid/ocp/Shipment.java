package com.ronak.javarealworld.solid.ocp;

import java.math.BigDecimal;

public record Shipment(String destinationCountry, BigDecimal declaredValue, boolean express) { }

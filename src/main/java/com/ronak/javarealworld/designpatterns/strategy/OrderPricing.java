package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;

public record OrderPricing(BigDecimal merchandiseTotal, BigDecimal discount, BigDecimal payableTotal) { }

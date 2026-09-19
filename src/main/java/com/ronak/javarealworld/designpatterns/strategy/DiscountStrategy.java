package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {
    BigDecimal calculateDiscount(Order order);
}

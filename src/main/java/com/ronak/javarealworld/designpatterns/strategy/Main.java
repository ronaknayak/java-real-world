package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        Order order = new Order("ORD-8931", CustomerTier.ENTERPRISE, new BigDecimal("120000.00"));
        OrderPricingService pricingService = new OrderPricingService(new EnterpriseContractDiscountStrategy());

        System.out.println(pricingService.price(order));
    }
}

package com.ronak.javarealworld.designpatterns.strategy;

import java.math.BigDecimal;

public final class OrderPricingService {
    private final DiscountStrategy discountStrategy;

    public OrderPricingService(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public OrderPricing price(Order order) {
        BigDecimal discount = discountStrategy.calculateDiscount(order);
        if (discount.signum() < 0 || discount.compareTo(order.merchandiseTotal()) > 0) {
            throw new IllegalStateException("Discount strategy returned an invalid discount");
        }
        return new OrderPricing(order.merchandiseTotal(), discount, order.merchandiseTotal().subtract(discount));
    }
}

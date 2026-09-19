package com.ronak.javarealworld.designpatterns.criteria;

import java.math.BigDecimal;
import java.util.Objects;

/** A customer account that can be selected for an operational campaign. */
public record Customer(String id, String name, String region, boolean active, BigDecimal monthlySpend) {
    public Customer {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(region, "region must not be null");
        Objects.requireNonNull(monthlySpend, "monthlySpend must not be null");
        if (monthlySpend.signum() < 0) {
            throw new IllegalArgumentException("monthlySpend must not be negative");
        }
    }
}
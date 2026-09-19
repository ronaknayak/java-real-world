package com.ronak.javarealworld.designpatterns.criteria;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

/** Factory methods for business criteria used by customer-selection workflows. */
public final class CustomerCriteria {
    private CustomerCriteria() { }

    public static CustomerCriterion active() {
        return Customer::active;
    }

    public static CustomerCriterion inRegion(String region) {
        String expectedRegion = normalizeRegion(region);
        return customer -> normalizeRegion(customer.region()).equals(expectedRegion);
    }

    public static CustomerCriterion spendingAtLeast(BigDecimal minimumSpend) {
        Objects.requireNonNull(minimumSpend, "minimumSpend must not be null");
        if (minimumSpend.signum() < 0) {
            throw new IllegalArgumentException("minimumSpend must not be negative");
        }
        return customer -> customer.monthlySpend().compareTo(minimumSpend) >= 0;
    }

    private static String normalizeRegion(String region) {
        Objects.requireNonNull(region, "region must not be null");
        String normalized = region.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("region must not be blank");
        }
        return normalized.toUpperCase(Locale.ROOT);
    }
}
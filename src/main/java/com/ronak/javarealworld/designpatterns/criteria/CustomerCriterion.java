package com.ronak.javarealworld.designpatterns.criteria;

import java.util.Objects;

/** A reusable rule for deciding whether a customer belongs in a result set. */
@FunctionalInterface
public interface CustomerCriterion {
    boolean matches(Customer customer);

    default CustomerCriterion and(CustomerCriterion other) {
        Objects.requireNonNull(other, "other criterion must not be null");
        return customer -> matches(customer) && other.matches(customer);
    }

    default CustomerCriterion or(CustomerCriterion other) {
        Objects.requireNonNull(other, "other criterion must not be null");
        return customer -> matches(customer) || other.matches(customer);
    }

    default CustomerCriterion negate() {
        return customer -> !matches(customer);
    }
}
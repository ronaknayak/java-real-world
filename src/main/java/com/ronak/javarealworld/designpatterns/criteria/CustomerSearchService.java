package com.ronak.javarealworld.designpatterns.criteria;

import java.util.List;
import java.util.Objects;

/** Application service that applies a supplied criterion without knowing its rules. */
public final class CustomerSearchService {
    public List<Customer> findMatching(List<Customer> customers, CustomerCriterion criterion) {
        Objects.requireNonNull(customers, "customers must not be null");
        Objects.requireNonNull(criterion, "criterion must not be null");
        return customers.stream().filter(criterion::matches).toList();
    }
}
package com.ronak.javarealworld.solid.dip;

import java.util.HashMap;
import java.util.Map;

public final class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public void save(Customer customer) {
        customers.put(customer.id(), customer);
    }
}

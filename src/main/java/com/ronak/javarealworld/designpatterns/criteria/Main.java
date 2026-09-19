package com.ronak.javarealworld.designpatterns.criteria;

import java.math.BigDecimal;
import java.util.List;

/** Run target for the Criteria (Filter) pattern example. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var customers = List.of(
                new Customer("CUS-101", "Aarav Shah", "IN", true, new BigDecimal("3200.00")),
                new Customer("CUS-102", "Mia Chen", "SG", true, new BigDecimal("900.00")),
                new Customer("CUS-103", "Olivia Martin", "FR", false, new BigDecimal("4100.00")),
                new Customer("CUS-104", "Noah Wilson", "US", true, new BigDecimal("5100.00")));

        CustomerCriterion prioritySupportCustomers = CustomerCriteria.active()
                .and(CustomerCriteria.inRegion("IN").or(CustomerCriteria.inRegion("SG")))
                .and(CustomerCriteria.spendingAtLeast(new BigDecimal("1000.00")));

        var searchService = new CustomerSearchService();
        var matchingCustomers = searchService.findMatching(customers, prioritySupportCustomers);

        System.out.println("Priority-support customers:");
        matchingCustomers.forEach(customer -> System.out.printf("%s (%s)%n", customer.name(), customer.id()));
    }
}
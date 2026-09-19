package com.ronak.javarealworld.oops.abstracts.templatemethodpattern;

import java.util.List;

/** Run target for the Template Method pattern example. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var request = new FulfillmentRequest("ORD-4821", "Northstar Retail", 3);
        List<AbstractOrderFulfillment> workflows = List.of(
                new StandardOrderFulfillment(),
                new ExpressOrderFulfillment());

        workflows.stream()
                .map(workflow -> workflow.fulfill(request))
                .forEach(System.out::println);
    }
}
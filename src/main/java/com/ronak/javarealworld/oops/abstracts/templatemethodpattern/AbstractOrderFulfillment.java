package com.ronak.javarealworld.oops.abstracts.templatemethodpattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Template Method base class: the final method fixes the business workflow,
 * while protected hooks let subclasses customize selected steps.
 */
public abstract class AbstractOrderFulfillment {
    public final FulfillmentReceipt fulfill(FulfillmentRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        var steps = new ArrayList<String>();
        validate(request, steps);
        reserveInventory(request, steps);
        prepareShipment(request, steps);
        dispatch(request, steps);
        notifyCustomer(request, steps);
        return new FulfillmentReceipt(request.orderId(), serviceLevel(), steps);
    }

    protected abstract String serviceLevel();

    protected abstract void prepareShipment(FulfillmentRequest request, List<String> steps);

    protected void validate(FulfillmentRequest request, List<String> steps) {
        steps.add("Validated order " + request.orderId());
    }

    protected void reserveInventory(FulfillmentRequest request, List<String> steps) {
        steps.add("Reserved " + request.itemCount() + " item(s)");
    }

    protected void dispatch(FulfillmentRequest request, List<String> steps) {
        steps.add("Dispatched using " + serviceLevel() + " delivery");
    }

    protected void notifyCustomer(FulfillmentRequest request, List<String> steps) {
        steps.add("Notified " + request.customerName());
    }
}
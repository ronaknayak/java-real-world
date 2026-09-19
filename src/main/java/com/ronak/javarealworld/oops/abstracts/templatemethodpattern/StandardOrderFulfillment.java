package com.ronak.javarealworld.oops.abstracts.templatemethodpattern;

import java.util.List;

/** Standard fulfillment customizes only the shipment-preparation hook. */
public final class StandardOrderFulfillment extends AbstractOrderFulfillment {
    @Override
    protected String serviceLevel() {
        return "standard";
    }

    @Override
    protected void prepareShipment(FulfillmentRequest request, List<String> steps) {
        steps.add("Packed shipment in the standard queue");
    }
}
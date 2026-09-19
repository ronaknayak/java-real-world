package com.ronak.javarealworld.oops.abstracts.templatemethodpattern;

import java.util.List;

/** Express fulfillment reuses the workflow but prioritizes shipment preparation. */
public final class ExpressOrderFulfillment extends AbstractOrderFulfillment {
    @Override
    protected String serviceLevel() {
        return "express";
    }

    @Override
    protected void prepareShipment(FulfillmentRequest request, List<String> steps) {
        steps.add("Packed shipment in the express queue");
    }
}
package com.ronak.javarealworld.solid.violations.isp;

/** A tracking-only integration is forced to implement operations it cannot provide. */
public final class TrackingOnlyCarrierAdapter implements CarrierOperations {
    @Override
    public String trackingStatus(String trackingNumber) {
        return trackingNumber + " is in transit";
    }

    @Override
    public String purchaseLabel(String orderNumber) {
        throw new UnsupportedOperationException("This carrier integration only supports tracking");
    }

    @Override
    public String proofOfDelivery(String trackingNumber) {
        throw new UnsupportedOperationException("This carrier integration only supports tracking");
    }
}

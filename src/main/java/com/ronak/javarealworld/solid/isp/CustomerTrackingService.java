package com.ronak.javarealworld.solid.isp;

public final class CustomerTrackingService {
    private final ShipmentTrackingGateway trackingGateway;

    public CustomerTrackingService(ShipmentTrackingGateway trackingGateway) {
        this.trackingGateway = trackingGateway;
    }

    public String currentStatus(String trackingNumber) {
        return trackingGateway.trackingStatus(trackingNumber);
    }
}

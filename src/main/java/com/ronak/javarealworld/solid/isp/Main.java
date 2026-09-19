package com.ronak.javarealworld.solid.isp;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var carrierGateway = new CarrierGateway();
        var trackingService = new CustomerTrackingService(carrierGateway);
        System.out.println("Label: " + carrierGateway.purchaseLabel("ORD-4821"));
        System.out.println("Status: " + trackingService.currentStatus("TRK-94721"));
    }
}

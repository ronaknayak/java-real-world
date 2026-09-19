package com.ronak.javarealworld.solid.violations.isp;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        CarrierOperations carrier = new TrackingOnlyCarrierAdapter();
        System.out.println(carrier.trackingStatus("TRK-94721"));
        try {
            carrier.purchaseLabel("ORD-4821");
        } catch (UnsupportedOperationException exception) {
            System.out.println("Violation: " + exception.getMessage());
        }
    }
}

package com.ronak.javarealworld.solid.violations.isp;

public interface CarrierOperations {
    String trackingStatus(String trackingNumber);

    String purchaseLabel(String orderNumber);

    String proofOfDelivery(String trackingNumber);
}

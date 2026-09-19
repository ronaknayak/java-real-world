package com.ronak.javarealworld.solid.isp;

public final class CarrierGateway implements ShipmentTrackingGateway, ShippingLabelGateway, DeliveryProofGateway {
    @Override
    public String trackingStatus(String trackingNumber) {
        return trackingNumber + " is out for delivery";
    }

    @Override
    public String purchaseLabel(String orderNumber) {
        return "LABEL-" + orderNumber;
    }

    @Override
    public String proofOfDelivery(String trackingNumber) {
        return "Signed proof for " + trackingNumber;
    }
}

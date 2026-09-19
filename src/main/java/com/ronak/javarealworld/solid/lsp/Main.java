package com.ronak.javarealworld.solid.lsp;

public final class Main {
    private Main() { }

    public static void main(String[] args) {
        var service = new CustomerAlertService();
        var notification = new Notification("customer@acme.example", "Your order has shipped.");
        System.out.println(service.alert(new EmailNotificationChannel(), notification));
        System.out.println(service.alert(new SmsNotificationChannel(), notification));
    }
}

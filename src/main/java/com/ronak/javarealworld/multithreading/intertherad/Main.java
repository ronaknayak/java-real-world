package com.ronak.javarealworld.multithreading.intertherad;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {

        OrderProcessingWorker worker = new OrderProcessingWorker();

        worker.start();

        Thread.sleep(1000);

        worker.submitOrder("ORD-1001");
        worker.submitOrder("ORD-1002");
        worker.submitOrder("ORD-1003");

        Thread.sleep(3000);

        System.out.println("Requesting worker shutdown...");

        worker.shutdown();

        System.out.println("Worker stopped successfully.");
    }
}
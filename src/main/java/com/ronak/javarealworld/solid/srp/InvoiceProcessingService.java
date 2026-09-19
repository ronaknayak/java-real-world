package com.ronak.javarealworld.solid.srp;

import java.math.BigDecimal;

public final class InvoiceProcessingService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceTotalCalculator totalCalculator;
    private final InvoiceEmailSender emailSender;

    public InvoiceProcessingService(InvoiceRepository invoiceRepository, InvoiceTotalCalculator totalCalculator,
                                    InvoiceEmailSender emailSender) {
        this.invoiceRepository = invoiceRepository;
        this.totalCalculator = totalCalculator;
        this.emailSender = emailSender;
    }

    public void process(Invoice invoice) {
        BigDecimal total = totalCalculator.calculate(invoice);
        invoiceRepository.save(invoice);
        emailSender.send(invoice, total);
    }
}

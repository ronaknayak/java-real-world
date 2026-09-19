# Update log

This file documents every requested program addition. Keep `README.md` for project setup and conventions; use this log for a program's problem, design, important classes, run target, and takeaways.

## 2026-09-19 — SOLID principles

### Single Responsibility Principle — `solid.srp`

**Problem:** Process customer invoices without mixing tax calculation, persistence, and email delivery.

**Design:** `InvoiceProcessingService` coordinates `InvoiceTotalCalculator`, `InvoiceRepository`, and `InvoiceEmailSender`, each with one reason to change.

**Run:** `com.ronak.javarealworld.solid.srp.Main`

**Takeaway:** Tax, database, and email-provider changes remain isolated.

### Open/Closed Principle — `solid.ocp`

**Problem:** Quote domestic and international e-commerce shipments while allowing new pricing rules.

**Design:** `ShippingQuoteService` selects a `ShippingRatePolicy`; new pricing rules are additional implementations.

**Run:** `com.ronak.javarealworld.solid.ocp.Main`

**Takeaway:** Add a policy rather than modifying the quote service.

### Liskov Substitution Principle — `solid.lsp`

**Problem:** Send customer order alerts through different communication providers.

**Design:** Both `EmailNotificationChannel` and `SmsNotificationChannel` honor the `NotificationChannel` delivery contract.

**Run:** `com.ronak.javarealworld.solid.lsp.Main`

**Takeaway:** `CustomerAlertService` substitutes channels without client special cases.

### Interface Segregation Principle — `solid.isp`

**Problem:** Let tracking screens use carrier status without coupling to labels or proof-of-delivery APIs.

**Design:** Carrier capabilities are split into `ShipmentTrackingGateway`, `ShippingLabelGateway`, and `DeliveryProofGateway`; `CustomerTrackingService` uses only tracking.

**Run:** `com.ronak.javarealworld.solid.isp.Main`

**Takeaway:** Clients depend only on operations they need.

### Dependency Inversion Principle — `solid.dip`

**Problem:** Register a customer without coupling business policy to a database or message provider.

**Design:** `CustomerRegistrationService` depends on `CustomerRepository` and `WelcomeMessageSender` abstractions; `Main` wires implementations.

**Run:** `com.ronak.javarealworld.solid.dip.Main`

**Takeaway:** Infrastructure changes do not alter registration policy.

## 2026-09-19 — SOLID violations

The packages under `solid.violations` are intentional anti-pattern demonstrations. They are executable for learning and are not templates for production code.

### SRP violation — `solid.violations.srp`

`InvoiceManager` calculates tax, simulates database persistence, and sends an invoice email. One change to billing, storage, or notification affects the same class.

**Run:** `com.ronak.javarealworld.solid.violations.srp.Main`

### OCP violation — `solid.violations.ocp`

`ShippingQuoteCalculator` contains country and delivery-type conditionals. Adding a country, carrier, or pricing rule means modifying and retesting that class.

**Run:** `com.ronak.javarealworld.solid.violations.ocp.Main`

### LSP violation — `solid.violations.lsp`

`StoreCreditRefundMethod` implements `RefundMethod` but throws when asked to refund to an original external payment method, violating clients' expectation of the interface.

**Run:** `com.ronak.javarealworld.solid.violations.lsp.Main`

### ISP violation — `solid.violations.isp`

`TrackingOnlyCarrierAdapter` must implement label and proof operations it does not support because `CarrierOperations` is too broad.

**Run:** `com.ronak.javarealworld.solid.violations.isp.Main`

### DIP violation — `solid.violations.dip`

`CustomerRegistrationService` directly constructs PostgreSQL and email-provider details, binding policy to infrastructure and making replacement or unit testing harder.

**Run:** `com.ronak.javarealworld.solid.violations.dip.Main`

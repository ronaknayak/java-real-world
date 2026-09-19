# Design patterns

This document records production-style examples under `designpatterns`.

## 2026-09-19 â€” Factory pattern (`designpatterns.factory`)

**Problem:** An e-commerce checkout must choose the appropriate payment integration for card, bank-transfer, and wallet payments without filling checkout policy with conditional provider construction.

**Design:** `PaymentProcessorFactory` receives configured `PaymentProcessor` implementations and returns one based on `PaymentMethod`. `CheckoutService` delegates processing to the selected processor.

**Important classes:**

- `PaymentProcessor`: common contract for all payment integrations.
- `PaymentProcessorFactory`: validates the processor registry and selects a processor.
- `CheckoutService`: application policy that stays independent of concrete payment integrations.

**Run:** `com.ronak.javarealworld.designpatterns.factory.Main`

**Takeaway:** The factory centralizes object selection. New payment methods require a processor implementation and composition update, without changing checkout flow.

## 2026-09-19 â€” Strategy pattern (`designpatterns.strategy`)

**Problem:** An ordering platform applies different commercial discount rules for standard, gold, and enterprise customers. The pricing workflow should not be filled with customer-tier conditionals.

**Design:** `OrderPricingService` accepts a `DiscountStrategy` and validates its result. `StandardCustomerDiscountStrategy`, `GoldCustomerDiscountStrategy`, and `EnterpriseContractDiscountStrategy` encapsulate individual commercial rules.

**Important classes:**

- `DiscountStrategy`: contract for a pricing rule.
- `OrderPricingService`: stable pricing workflow, independent of discount-rule details.
- `EnterpriseContractDiscountStrategy`: demonstrates a percentage rule with a contractual maximum discount.

**Run:** `com.ronak.javarealworld.designpatterns.strategy.Main`

**Takeaway:** Select a strategy at application composition time and keep each changing pricing rule in a focused implementation.

## 2026-09-19 — Singleton pattern (`designpatterns.singleton`)

**Problem:** An account-notification service needs one shared source for its sender configuration. A conventional singleton must remain correct during concurrent access and resist accidental duplication.

**Design:** `VulnerableNotificationSettings` intentionally shows how reflection, serialization, and cloning break a basic singleton. `ThreadSafeNotificationSettings` uses the initialization-on-demand holder idiom for lazy, thread-safe access, adds a constructor guard, `readResolve`, and rejects cloning. `NotificationSettings` is an enum singleton—the preferred option when its API is suitable—because the JVM also protects it from reflection and serialization attacks.

**Important classes:**

- `VulnerableNotificationSettings`: deliberately unsafe baseline used by the runnable demonstration.
- `ThreadSafeNotificationSettings`: lazy holder-based singleton with serialization and clone defenses.
- `NotificationSettings`: enum-based singleton, recommended when an enum is appropriate.

**Run:** `com.ronak.javarealworld.designpatterns.singleton.Main`

**Takeaway:** Use the holder idiom for lazy thread-safe singleton creation. If reflection and serialization resilience matter, prefer an enum singleton; the constructor guard only stops reflection after the ordinary instance has already been initialized.

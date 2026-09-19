# Java 8+ language examples

This document records Java 8+ language examples under `java8`.

## 2026-09-20 - Custom functional interface (`java8.functionalinterface`)

**Problem:** A product catalog needs several checks before a product can be published. A failed check must carry a checked, domain-specific error, and checks should be easy to combine.

**Reason to use:** A custom functional interface is useful when a generic `Function` or `Consumer` does not clearly describe the business action. It can give the action a meaningful name, declare a checked exception, and provide domain-specific composition methods.

**Design:** `ProductValidationRule` declares one `validate` method, plus default `andThen` and `named` methods. `ProductPublicationValidator` applies the composed rule, while `Main` supplies price and SKU checks as lambdas.

**Important classes:**

- `ProductValidationRule`: functional interface with a checked exception and fluent defaults.
- `ProductPublicationValidator`: engine that applies the composed validation rule.
- `Main`: client code using product-validation lambdas.

**Run:** `com.ronak.javarealworld.java8.functionalinterface.Main`

**Takeaway:** Use a custom functional interface when the domain meaning, checked failure, or composition is important to the contract.

## 2026-09-20 - Default methods (`java8.defaultmethod`)

**Problem:** A notification system already has email and SMS channel implementations. Later, every channel needs the same audited-delivery workflow without forcing existing classes to duplicate it.

**Reason to use:** A default method is useful when an interface needs shared behavior and existing implementations should continue working unchanged. Implementations can inherit the default or override it when their behavior is special.

**Design:** `NotificationChannel` keeps `send` as the required operation and adds default `sendWithAudit` and `channelName` methods. `EmailNotificationChannel` and `SmsNotificationChannel` implement delivery, while `NotificationService` uses the shared default workflow.

**Important classes:**

- `NotificationChannel`: interface with the required operation and reusable default behavior.
- `EmailNotificationChannel` and `SmsNotificationChannel`: implementations that inherit the defaults.
- `NotificationService`: client of the evolving interface.

**Run:** `com.ronak.javarealworld.java8.defaultmethod.Main`

**Takeaway:** Add a default method when most implementations need the same behavior and the interface may evolve over time.

## 2026-09-20 - Static methods inside an interface (`java8.staticmethod`)

**Problem:** Several payment processors need the same method parsing and request validation, but those helpers belong to the payment contract and do not use processor instance state.

**Reason to use:** An interface static method is useful for helper logic that conceptually belongs to the interface and is shared by its implementations. It must be called with the interface name, such as `PaymentProcessor.parseMethod(...)`; implementations do not inherit or override it.

**Design:** `PaymentProcessor` declares the instance `process` contract and the static `parseMethod` and `validateRequest` helpers. Card and bank-transfer processors implement `process` and call the shared validation explicitly through the interface.

**Important classes:**

- `PaymentProcessor`: interface with an instance contract and static helpers.
- `CardPaymentProcessor` and `BankTransferPaymentProcessor`: concrete implementations using the shared interface helper.
- `Main`: client code calling the static method through `PaymentProcessor`.

**Run:** `com.ronak.javarealworld.java8.staticmethod.Main`

**Takeaway:** Put a static method in an interface when the helper belongs to the contract, needs no object state, and should be called directly through that interface rather than inherited by implementations.
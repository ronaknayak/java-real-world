# OOP examples

This document records object-oriented programming examples under `oops`.

## 2026-09-19 - Interfaces (`oops.interfaces`)

**Problem:** An operations team wants to deliver the same report through email, Slack, and an audit archive. The dispatcher should support these unrelated channels without depending on or branching on their concrete classes.

**Reason to use:** An interface defines a capability or contract that unrelated classes can implement. It is useful when multiple implementations must be interchangeable and the calling code should depend only on the shared behavior.

**Design:** `ReportDeliveryChannel` declares the delivery capability. `EmailReportChannel`, `SlackReportChannel`, and `AuditFileReportChannel` implement it independently, while `ReportDispatcher` works only with the interface.

**Important classes:**

- `ReportDeliveryChannel`: shared contract for report delivery.
- `EmailReportChannel`, `SlackReportChannel`, and `AuditFileReportChannel`: independent implementations of that capability.
- `ReportDispatcher`: consumer that supports any channel implementation.

**Run:** `com.ronak.javarealworld.oops.interfaces.Main`

**Takeaway:** Program against the interface when callers need a capability and the implementation may vary or grow over time.
## 2026-09-19 - Abstract classes (`oops.abstracts`)

**Problem:** An operations team needs reports in HTML and plain text. Both formats share report metadata, construction rules, headers, and footer behavior, but each renders the report body differently.

**Reason to use:** An abstract class is used when related classes share a real base type, state, constructors, or protected behavior. It provides the common implementation once and leaves only the varying behavior to subclasses.

**Design:** `AbstractReportFormatter` owns the formatter name, creation time, validation, formatting workflow, and protected HTML escaping helper. `HtmlReportFormatter` and `PlainTextReportFormatter` inherit that shared behavior and implement only `renderBody`.

**Important classes:**

- `AbstractReportFormatter`: common base type with shared state, constructor, template workflow, and protected behavior.
- `HtmlReportFormatter` and `PlainTextReportFormatter`: concrete subclasses that provide format-specific body rendering.
- `ReportDocument`: immutable report data shared by all formatters.

**Run:** `com.ronak.javarealworld.oops.abstracts.Main`

**Takeaway:** Choose an abstract class when inheritance represents a genuine “is-a” relationship and subclasses should share both implementation and a common contract.

## 2026-09-19 - Template Method pattern (`oops.abstracts.templatemethodpattern`)

**Problem:** An order platform must fulfill standard and express orders using the same reliable sequence: validate, reserve inventory, prepare, dispatch, and notify. Only shipment preparation and service-level details vary by fulfillment type.

**Reason to use:** Template Method is a behavioral design pattern used when related workflows follow the same overall algorithm but have a few variable steps. An abstract class defines the fixed sequence and lets subclasses customize selected protected hooks.

**Design:** `AbstractOrderFulfillment.fulfill` is the final template method, so callers cannot reorder the workflow. `StandardOrderFulfillment` and `ExpressOrderFulfillment` override only shipment preparation and service-level hooks.

**Important classes:**

- `AbstractOrderFulfillment`: owns the invariant fulfillment sequence and shared steps.
- `StandardOrderFulfillment` and `ExpressOrderFulfillment`: customize the variable fulfillment behavior.
- `FulfillmentReceipt`: captures the executed workflow steps.

**Run:** `com.ronak.javarealworld.oops.abstracts.templatemethodpattern.Main`

**Takeaway:** Use Template Method when subclasses share an algorithm and should vary only selected steps, keeping the sequence and common behavior in one base class.

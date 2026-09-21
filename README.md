# java-real-world

Production-style, standalone Java examples for learning backend engineering concepts. The project targets Java 17 and uses Maven.

## Topics

| Directory | Purpose |
| --- | --- |
| `solid/` | SOLID principle examples (`srp`, `ocp`, `lsp`, `isp`, `dip`) and runnable anti-patterns in `violations/`. |
| `designpatterns/` | Production-style design-pattern examples: `factory` and `strategy`. |
| `oops/` | Object-oriented programming examples, including interface and abstract-class capabilities. |
| `java8/` | Java functional-interface examples and Java 8+ language features. |


Each concept resides in its own package and supplies a `Main.java`, so it can be run directly from IntelliJ IDEA. [solid/solid.md](src/main/java/com/ronak/javarealworld/solid/solid.md), [designpatterns/designpatterns.md](src/main/java/com/ronak/javarealworld/designpatterns/designpatterns.md), and [oops/oops.md](src/main/java/com/ronak/javarealworld/oops/oops.md) document their examples' business problem, design, important classes, run target, and takeaways.

## Open in IntelliJ IDEA

1. Select **File â†’ Open** and choose this `java-real-world` directory.
2. Import it as a Maven project when prompted.
3. Configure a Java 17 (or newer) SDK.
4. Open the desired concept's `Main.java` and run its `main` method.

## Build

```powershell
mvn compile
```

Verification is performed by the repository owner. Do not run Maven commands automatically after making changes.

## Conventions for new examples

- Package root: `com.ronak.javarealworld`.
- Use production-inspired business scenarios and preserve separation of concerns.
- Keep examples standalone; no Spring Boot or infrastructure unless the example explicitly needs it.
- Keep examples independently runnable from IntelliJ IDEA; tests are added only when explicitly requested.
- Do not change unrelated examples while adding a new topic.
- Document each topic addition in its topic log (for example, `solid/solid.md` or `designpatterns/designpatterns.md`); do not use README files as an addition log.
- In each topic-log entry, include a short **Reason to use** explanation in simple words.


## Codex Execution Instructions

- Work autonomously within the current project workspace.
- You are authorized to create, modify, rename, and delete files required for the task.
- Do not ask for confirmation before editing project files.
- Do not stop to request permission for routine file changes.
- Run the required Maven commands, tests, and validations automatically.
- If an implementation requires changes to multiple files, make all required changes directly.
- Continue working until the requested task is fully implemented and verified.
- Only ask for clarification when the requirement itself is ambiguous or a required decision cannot reasonably be determined from the existing project instructions.
- Do not modify files outside the current project workspace unless explicitly requested.

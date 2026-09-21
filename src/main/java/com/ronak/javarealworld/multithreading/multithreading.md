# Multithreading examples

## Synchronized inventory reservation

### Business problem

Two order-processing workers can try to reserve the same inventory at the same time. Without coordination, both workers could see enough stock and oversell the item.

### Design

`Inventory.reserve` is a synchronized instance method. A worker must acquire the inventory object's monitor before it checks and reduces stock, so the check-and-update operation is atomic. `availableUnits` is also synchronized to read the shared value safely after the workers finish.

### Important classes

- `Inventory` owns the shared stock and synchronizes reservations.
- `Main` starts two order-processing threads that compete for the same eight units.

### Run target

`com.ronak.javarealworld.multithreading.synchronizedd.Main`

### Reason to use

Use `synchronized` when a small, related set of reads and writes must happen as one operation, such as checking inventory and reserving it.

### Takeaway

The `synchronized` keyword protects a critical section with an object's monitor. Only one thread can execute a synchronized instance method on the same object at a time.

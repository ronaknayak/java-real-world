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

## Static synchronized settlement-batch allocation

### Business problem

Settlement workers may use different service instances while allocating numbers from one shared batch sequence. The sequence must not issue duplicate numbers.

### Design

`SettlementBatchSequence.nextBatchNumber` is a `static synchronized` method. It locks `SettlementBatchSequence.class`, rather than either service instance, so both worker threads coordinate even though they each use a different instance.

### Important classes

- `SettlementBatchSequence` owns the class-wide counter and its class-level lock.
- `Main` creates two service instances to show that instance identity does not change the static lock.

### Run target

`com.ronak.javarealworld.multithreading.staticsynchronized.Main`

### Reason to use

Use `static synchronized` when mutable data belongs to the class and every instance must coordinate access to it.

### Takeaway

A static synchronized method is equivalent to synchronizing on the class object: `synchronized (SettlementBatchSequence.class)`.

## Volatile shutdown visibility

### Business problem

A fulfillment polling worker runs on its own thread and needs to stop promptly when the application begins a graceful shutdown. The shutdown request comes from a different thread.

### Design

`PollingWorker.shutdownRequested` is `volatile`. The application thread writes `true` through `requestShutdown`, and the polling thread reads the same field in its loop. The volatile write is visible to later volatile reads, so the worker can observe the request without synchronizing the whole loop.

### Important classes

- `PollingWorker` owns the volatile shutdown signal and the polling loop.
- `Main` starts the worker, waits until it is running, and requests shutdown from the main thread.

### Run target

`com.ronak.javarealworld.multithreading.visibility.Main`

### Reason to use

Use `volatile` for a simple shared state signal, such as a stop flag, when threads only need the latest value and do not need a multi-step update to be atomic.

### Takeaway

`volatile` provides visibility and ordering for one field; it does not make operations such as `count++` atomic. Use `synchronized`, locks, or atomic classes when a read-modify-write operation must be coordinated.

## Private lock dispatch queue

### Business problem

Several order-processing workers add completed orders to one dispatch queue. The queue must not lose requests or corrupt its internal ordering when workers submit orders at the same time.

### Design

`DispatchQueue` keeps `private final Object lock = new Object();` and synchronizes every access to its mutable queue on that object. Because the lock is private and final, callers cannot acquire it, replace it, or accidentally make the queue's synchronization depend on the public object monitor.

### Important classes

- `DispatchQueue` owns the dispatch IDs, the private lock, and synchronized queue operations.
- `Main` starts two order workers that submit dispatch requests concurrently.

### Run target

`com.ronak.javarealworld.multithreading.privatelock.Main`

### Reason to use

Use a private lock when a class needs to protect its own mutable state without exposing its locking choice to other code.

### Takeaway

A `private final` lock keeps synchronization encapsulated. Do not lock on publicly reachable objects such as `this`, strings, or boxed values when unrelated code could acquire the same monitor.

## ReentrantLock bounded dispatch queue

### Business problem

Order-intake workers submit completed orders while fulfillment workers remove them for dispatch. The queue has limited capacity, so workers need a safe way to wait, stop waiting during shutdown, or fail fast when the service is busy.

### Design

`DispatchQueue` uses a fair `ReentrantLock` and two conditions: `dispatchAvailable` for waiting fulfillment workers and `capacityAvailable` for waiting intake workers. It releases the lock in a `finally` block on every path. `submit` and `take` use `lockInterruptibly` so shutdown can interrupt blocked workers. `trySubmit` uses immediate `tryLock` for fail-fast work, while its timed overload uses `tryLock(timeout, unit)` and `awaitNanos` to limit both lock and capacity waiting to one deadline.

### Important classes

- `DispatchQueue` owns the fair reentrant lock, conditions, bounded queue, and queue metrics.
- `Main` demonstrates blocking submission, immediate fail-fast submission, timed submission, and consumption.

### Run target

`com.ronak.javarealworld.multithreading.lockapi.reentrant.Main`

### Reason to use

Use `ReentrantLock` when a synchronized block needs features such as interruptible locking, non-blocking or timed attempts, conditions, fairness, or lock metrics.

### Takeaway

Always pair a successful `lock`, `lockInterruptibly`, or `tryLock` with `unlock` in a `finally` block. Use conditions with a `while` loop because a waiting thread must recheck its queue state after it wakes.

## ReentrantReadWriteLock product catalog

### Business problem

An ecommerce catalog serves many availability lookups while a much smaller number of import jobs refresh product data. Serializing every lookup behind every refresh reduces throughput unnecessarily.

### Design

`ProductCatalog` uses a fair `ReentrantReadWriteLock`. Lookup and snapshot methods acquire its read lock, allowing multiple readers to run together. Refresh and availability-update methods acquire its write lock, which is exclusive and publishes a complete catalog change atomically. Returned availability objects and snapshots are immutable, so callers cannot change catalog data after the lock is released.

### Important classes

- `ProductCatalog` protects read-heavy product availability with separate read and write locks.
- `ProductAvailability` is an immutable catalog value.
- `Main` starts multiple readers and one catalog-refresh writer.

### Run target

`com.ronak.javarealworld.multithreading.lockapi.reentrantreadwrite.Main`

### Reason to use

Use `ReentrantReadWriteLock` when reads are much more common than writes and concurrent readers can safely use the same stable data.

### Takeaway

The read lock allows readers to share access; the write lock excludes both readers and other writers. Always release either lock in a `finally` block, and avoid attempting to upgrade from a read lock to a write lock because it can deadlock.

## StampedLock delivery-location tracker

### Business problem

Customers and support systems frequently read a driver's latest location, while the driver application writes new positions far less often. Taking a full read lock for every successful lookup adds avoidable contention.

### Design

`DeliveryLocationTracker.latestLocation` uses `tryOptimisticRead` to read the immutable location without blocking. It calls `validate` before returning the value and falls back to `readLock` when a concurrent writer invalidated the optimistic stamp. `recordLocation` uses `writeLock` for exclusive updates. `refreshIfStale` uses `tryConvertToWriteLock` to upgrade a read lock when possible, otherwise it releases the read lock and acquires the write lock before rechecking the condition.

### Important classes

- `DeliveryLocationTracker` combines optimistic reads, validated fallback reads, exclusive writes, and safe lock conversion.
- `DeliveryLocation` is an immutable driver-position value.
- `Main` runs concurrent tracking reads and refreshes an old location.

### Run target

`com.ronak.javarealworld.multithreading.lockapi.stampedlock.Main`

### Reason to use

Use `StampedLock` for highly read-heavy state when optimistic reads can usually succeed and readers can cheaply retry if a writer changes the state.

### Takeaway

An optimistic read is not a lock: always validate its stamp before trusting data read under it. `StampedLock` is not reentrant, so keep lock scopes small and never attempt nested acquisition by the same thread.

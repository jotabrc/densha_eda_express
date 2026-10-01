# densha EDA express
**Description**: Event-Driven Architecture (EDA) patterns in practice.

**Author**: João Carlos Roveda Ostrovski

---

# Patterns

## Transactional Outbox Pattern
Saves the domain entity and its corresponding event in the database within the same database transaction. An asynchronous process (Scheduler or CDC engine) then reads the event from the database and publishes it to the message broker.

**Purpose**: Guarantees atomic operations between domain entity updates and event publishing (both must succeed or fail together), preventing dual-write inconsistencies.

**Trade-offs**: 
* **Polling Approach**: Requires a scheduler that periodically polls the database, acquires row locks, and performs update/delete operations, adding I/O overhead to the database.
* **Log-Based CDC (Debezium)**: Eliminates polling overhead but introduces additional infrastructure complexity (Kafka Connect, WAL monitoring).
* **Consumer Idempotency**: Guarantees *at-least-once* delivery, requiring consumers to implement idempotency to handle duplicate events safely.

**Requirements**: 
* Entity table
* Outbox table
* Single transactional boundary (`@Transactional`) for persisting both entity and outbox event
* Polling mechanism (Spring `@Scheduled`) **OR** Log-Based CDC engine (Debezium)
* Cleanup policy for processed outbox events (scheduled purge or partition drop)

**Steps (Polling Variant)**:
1. Client request arrives.
2. Application initiates a single DB transaction.
3. Domain service updates the entity and inserts an event record into the Outbox table.
4. Entity and Outbox records are committed or rolled back together.
5. Client request completes.
6. External scheduler performs an Outbox table lookup (e.g., using row locks).
7. Unprocessed event is fetched and published to the Kafka topic.
8. Event is marked as processed or deleted from the Outbox table upon broker acknowledgement.

*Note: Alternatively, this pattern can be implemented without polling via Log-Based CDC using Debezium with Kafka Connect, or embedded within Spring via `debezium-embedded` (`SmartLifecycle`).*

[Implementation specification](.docs/transactional_outbox_pattern.md)

---

## Saga Choreography
Executes a distributed transaction across multiple microservices where each service executes its local transaction and publishes domain events that trigger the next step in other services.

**Purpose**: Solves multi-step distributed business processing without relying on Two-Phase Commit (2PC), distributed locks, or a centralized orchestrator.

**Trade-offs**: 
* **Complexity in Rollbacks**: Compensating transactions must be explicitly designed and executed in reverse order upon failure.
* **Lack of Central Visibility**: The overall state of a distributed workflow is scattered across multiple services, making debugging, testing, and tracing more challenging.

**Requirements**:
* Domain events indicating success or failure at each step.
* Reactive consumers listening to upstream events to trigger downstream operations or compensating transactions.

**Steps (Happy Path)**:
1. Client initiates transaction; Service A performs local work and emits `EventA_Succeeded`.
2. Service B listens to `EventA_Succeeded`, executes its local transaction, and emits `EventB_Succeeded`.
3. Service C listens to `EventB_Succeeded` and completes the final business step.

**Steps (Rollback / Compensation)**:
1. Service C encounters a business failure while handling `EventB_Succeeded` and emits `EventC_Failed`.
2. Service B listens to `EventC_Failed`, executes a local compensating transaction (undoing step B), and emits `EventB_Compensated`.
3. Service A listens to `EventB_Compensated`, executes a local compensating transaction (undoing step A), and marks the Saga as cancelled.

*Note: For workflows with high complexity or many branching steps, **Saga Orchestration** can be used instead to centralize workflow state management.*

---

## Command Query Responsibility Segregation (CQRS)
Separates write (Command) operations from read (Query) operations. In distributed setups, changes from the write-optimized data store are synchronized asynchronously to read-optimized stores (e.g., PostgreSQL to Elasticsearch or Redis).

**Purpose**: Optimizes storage engines independently for high-throughput systems, enabling fast transactional writes and scalable, low-latency reads.

**Trade-offs**: 
* **Eventual Consistency**: There is a propagation delay between data being committed to the write store and becoming visible in the read store.
* **Data Synchronization Failures**: Requires robust synchronization pipelines to recover from lag or schema mismatches.

**Requirements**:
* Write data store (optimized for ACID transactions and normalization).
* Read data store (optimized for denormalized queries, full-text search, or caching).
* Asynchronous event pipeline or CDC mechanism to propagate write events.

**Steps**:
1. Client issues a write request.
2. Command service validates business rules, writes to the Write Store, and emits a domain event (e.g., via Outbox).
3. Read consumer receives the event and updates/upserts the document in the Read Store (Elasticsearch/Redis).
4. Query services read directly from the Read Store.

*Note: CQRS can also be implemented at the code level within a single database by separating Write Models (Domain Entities/Aggregates) from Read Models (DTO Projections/Direct DB queries) without needing asynchronous event synchronization.*

---

## Non-Blocking Retry & Dead Letter Queue (DLQ)
Offloads failed events from the main topic to dedicated retry topics with exponential backoff delays, eventually parking unrecoverable events in a Dead Letter Queue (DLQ).

**Purpose**: Recovers from transient failures without blocking the main event consumption pipeline or losing unprocessable messages.

**Trade-offs**: 
* **Loss of Message Ordering**: While a failed message is being retried in a side topic, newer messages for the same partition continue to be processed on the main topic.
* **DLQ Governance**: Requires operational monitoring and tooling to inspect, re-drive, or purge messages parked in the DLQ.

**Requirements**:
* Retry topics with configured backoff delays (e.g., Spring Kafka `@RetryableTopic`).
* Dead Letter Queue (DLQ) topic or database table for terminal failures.

**Steps**:
1. Consumer encounters an exception while processing an event from the main topic.
2. The event is republished to a designated retry topic with a configured backoff delay.
3. Main topic continues processing subsequent events without blocking.
4. If retry attempts are exhausted, the event is moved to the DLQ topic (or a failure table) for manual inspection or deferred re-processing.
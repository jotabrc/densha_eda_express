# Command Query Responsibility Segregation (CQRS)

## Data writer

Data written normally in business logic processing, any persistence is saved directly to the write optimized data store.

For data that is being updated, its retrieved in the write optimized data stored as such to prevent inconsistency writes when consistency is key, but it is undesirable and should be avoided at all costs. If your system requires consistent reads before any update, then this pattern might not be the right choice.

## Data reader

Any data read requests are retrieved using the read optimized data store.

Data can have eventual consistency, which means that retrieved data might not be in its upmost recent state.

[CQRS specification](/transactional_outbox_pattern.md)


## Diagram

![](../Projects/densha_eda_express/.docs/images/densh_eda_express_command_query_responsibility_segregation.png)
# Advantages of immutability

- Immutable objects are thread-safe so you will not have any synchronization issues.

- Immutability makes it easier to parallelize your program as there are no conflicts among objects.

- The internal state of your program will be consistent even if you have exceptions.

- References to immutable objects can be cached as they are not going to change.


## Concurrency vs. Parallelism

**Concurrency** is when two or more tasks can start, run, and complete in overlapping time periods. It doesn't necessarily mean they'll ever both be running at the same instant. For example, multitasking on a single-core machine.

**Parallelism** is when tasks literally run at the same time, e.g., on a multicore processor.
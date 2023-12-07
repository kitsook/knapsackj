## Solving the Knapsack Problem with recursion in Java 

Wikipedia has [detailed description](https://en.wikipedia.org/wiki/Knapsack_problem) on the Knapsack problem and pseudo code on solving it.
However, while [implementing it with Elixir](https://exercism.org/tracks/elixir/exercises/knapsack), seems that using simple recursion yields cleaner code.
This is not the fastest way to solve the problem, but just easy to understand.

Basically, three return points within the recursion:

- if no items, result is 0 and return
- pop the first item. if item weight is over the max limit, return recursion result of the remaining items
- return the max between (current item value + recursion result with the remaining item) or (just the recursion result with the remaining items only)

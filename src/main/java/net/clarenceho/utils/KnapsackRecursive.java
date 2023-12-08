package net.clarenceho.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Solving the Knapsack problem with recursive. Slow and will cause stack overflow if there are lots
 * of items. But the implementation is easy to understand.
 */
public class KnapsackRecursive implements Knapsack {
    @Override
    public int maximumValue(List<Knapsack.Item> items, int maxWeight) {
        if (items.isEmpty()) {
            return 0;
        }

        List<Knapsack.Item> tail = new ArrayList<>(items);
        Knapsack.Item item = tail.remove(0);

        if (item.weight() > maxWeight) {
            return maximumValue(tail, maxWeight);
        }

        return Stream
            .concat(Stream.of(0).map(ignored -> item.value() + maximumValue(tail, maxWeight - item.weight())),
                Stream.of(0).map(ignored -> maximumValue(tail, maxWeight)))
            .max(Integer::compare)
            .orElseThrow();
    }
}

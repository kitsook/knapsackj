package net.clarenceho.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Based on pseudo code from wiki <a href="https://en.wikipedia.org/wiki/Knapsack_problem">...</a>.
 * Store calculated value to minimize recursion.
 */
public class KnapsackQuick implements Knapsack {
    Map<Key, Integer> values = new HashMap<>();

    @Override
    public int maximumValue(List<Item> items, int maxWeight) {
        Key solution = new Key(items.size(), maxWeight);
        calcValues(solution, items);

        return Objects.requireNonNull(values.get(solution));
    }

    private void calcValues(Key key, List<Item> items) {
        if (key.numItem == 0 || key.weight <= 0) {
            values.put(key, 0);
            return;
        }

        Key keyLessItem = new Key(key.numItem - 1, key.weight);
        if (!values.containsKey(keyLessItem)) {
            calcValues(keyLessItem, items);
        }

        if (items.get(key.numItem - 1).weight() > key.weight) {
            values.put(key, values.get(keyLessItem));
        } else {
            Key keyCalced = new Key(key.numItem - 1, key.weight - items.get(key.numItem - 1).weight());
            if (!values.containsKey(keyCalced)) {
                calcValues(keyCalced, items);
            }
            values.put(key, Math.max(values.get(keyLessItem), values.get(keyCalced) + items.get(key.numItem - 1).value()));
        }
    }

    private record Key(int numItem, int weight) {}
}

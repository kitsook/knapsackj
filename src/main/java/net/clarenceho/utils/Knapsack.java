package net.clarenceho.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Knapsack {
    public static int maximumValue(List<Item> items, int maxWeight) {
        if (items.isEmpty()) {
            return 0;
        }

        List<Item> tail = new ArrayList<>(items);
        Item item = tail.remove(0);

        if (item.weight > maxWeight) {
            return maximumValue(tail, maxWeight);
        }

        return Stream
            .concat(Stream.of(0).map(ignored -> item.value + maximumValue(tail, maxWeight - item.weight)),
                Stream.of(0).map(ignored -> maximumValue(tail, maxWeight)))
            .max(Integer::compare)
            .orElseThrow();
    }

    public record Item(int weight, int value) {}
}


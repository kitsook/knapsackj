package net.clarenceho.utils;

import java.util.List;

public interface Knapsack {

    int maximumValue(List<Knapsack.Item> items, int maxWeight);
    record Item(int weight, int value) {}
}


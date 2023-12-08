package net.clarenceho.utils;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

// include test cases from https://exercism.org/tracks/elixir/exercises/knapsack
@ExtendWith(MockitoExtension.class)
class KnapsackTest {
    public static Stream<Knapsack> sourceMethod() {
        return Stream.of(new KnapsackRecursive(), new KnapsackQuick());
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void emptyItemsShouldReturnZero(Knapsack knapsack) {
        int result = knapsack.maximumValue(Collections.emptyList(), 100);
        assertThat(result).isZero();
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void singleItemWithinMax(Knapsack knapsack) {
        int result = knapsack.maximumValue(Collections.singletonList(new Knapsack.Item(10, 42)),
            10);
        assertThat(result).isEqualTo(42);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void singleItemExceedMax(Knapsack knapsack) {
        int result = knapsack.maximumValue(Collections.singletonList(new Knapsack.Item(10, 42)),
            8);
        assertThat(result).isZero();
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void twoItemsWithinMax(Knapsack knapsack) {
        List<Knapsack.Item> items = Arrays.asList(
            new Knapsack.Item(7, 42),
            new Knapsack.Item(13, 1337)
        );
        int result = knapsack.maximumValue(items, 25);
        assertThat(result).isEqualTo(1379);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void cannotBeGreedyByWeight(Knapsack knapsack) {
        List<Knapsack.Item> items = Arrays.asList(
            new Knapsack.Item(2, 5),
            new Knapsack.Item(2, 5),
            new Knapsack.Item(2, 5),
            new Knapsack.Item(2, 5),
            new Knapsack.Item(10, 21)
        );
        int result = knapsack.maximumValue(items, 10);
        assertThat(result).isEqualTo(21);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void cannotBeGreedyByValue(Knapsack knapsack) {
        List<Knapsack.Item> items = Arrays.asList(
            new Knapsack.Item(2, 20),
            new Knapsack.Item(2, 20),
            new Knapsack.Item(2, 20),
            new Knapsack.Item(2, 20),
            new Knapsack.Item(10, 50)
        );
        int result = knapsack.maximumValue(items, 10);
        assertThat(result).isEqualTo(80);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void lessWeightIsMore(Knapsack knapsack) {
        List<Knapsack.Item> items = Arrays.asList(
            new Knapsack.Item(5, 10),
            new Knapsack.Item(4, 40),
            new Knapsack.Item(6, 30),
            new Knapsack.Item(4, 50)
        );
        int result = knapsack.maximumValue(items, 10);
        assertThat(result).isEqualTo(90);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void moreItems(Knapsack knapsack) {
        List<Knapsack.Item> items = Arrays.asList(
            new Knapsack.Item(25, 350),
            new Knapsack.Item(35, 400),
            new Knapsack.Item(45, 450),
            new Knapsack.Item(5, 20),
            new Knapsack.Item(25, 70),
            new Knapsack.Item(3, 8),
            new Knapsack.Item(2, 5),
            new Knapsack.Item(2, 5)
        );
        int result = knapsack.maximumValue(items, 104);
        assertThat(result).isEqualTo(900);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void evenMoreItems(Knapsack knapsack) {
        List<Knapsack.Item> items = Arrays.asList(
            new Knapsack.Item(70, 135),
            new Knapsack.Item(73, 139),
            new Knapsack.Item(77, 149),
            new Knapsack.Item(80, 150),
            new Knapsack.Item(82, 156),
            new Knapsack.Item(87, 163),
            new Knapsack.Item(90, 173),
            new Knapsack.Item(94, 184),
            new Knapsack.Item(98, 192),
            new Knapsack.Item(106, 201),
            new Knapsack.Item(110, 210),
            new Knapsack.Item(113, 214),
            new Knapsack.Item(115, 221),
            new Knapsack.Item(118, 229),
            new Knapsack.Item(120, 240)
        );
        int result = knapsack.maximumValue(items, 750);
        assertThat(result).isEqualTo(1458);
    }

    @ParameterizedTest
    @MethodSource("sourceMethod")
    void aThousandInKnapsack(Knapsack knapsack) {
        // don't run the full recursive implementation
        assumeFalse(knapsack instanceof KnapsackRecursive);

        List<Knapsack.Item> items = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            items.add(new Knapsack.Item(1, 2));
        }
        int result = knapsack.maximumValue(items, 1000);
        assertThat(result).isEqualTo(2000);
    }
}

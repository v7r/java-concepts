package org.example.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingQuiz {
    public static void main(String[] args) {
        List<String> m = Arrays.asList("car", "truck", "car",
                "bicycle", "car", "truck", "motorcycle");
        Map<String, Long> o =
                m.stream().collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
        System.out.println(o);
    }
}

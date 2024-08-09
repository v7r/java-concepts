package org.example.streams;

import java.util.stream.Stream;

public class StreamConcat {
    public static void main(String[] args) {
        Stream<String> s1 = Stream.of("A","B","C","B");
        Stream<String> s2 = Stream.of("A","D","E");
        Stream.concat(s1, s2).parallel().distinct().forEach(System.out::print);
    }
}

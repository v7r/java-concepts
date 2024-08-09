package org.example.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamLines {
    public static void main(String[] args) throws IOException {
        Path p = new File("data.txt").toPath();
        Stream<String> lines = Files.lines(p);
        String data = lines.collect(Collectors.joining("-"));
        System.out.println(data);
        String data2 = Files.readAllLines(p).get(2);
        System.out.println(data2);
    }
}

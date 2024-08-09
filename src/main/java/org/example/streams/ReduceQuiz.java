package org.example.streams;

import java.util.List;
import java.util.function.Function;

public class ReduceQuiz {
    public String attach1(List<String> data) {
        return data.parallelStream().reduce("w",
                (n,m) -> n+m,
                String::concat);
    }

    public String attach2(List<String> data) {
        return data.parallelStream().reduce(
                (l,p) -> l+p).get();
    }

    public Integer attach3(List<Integer> data) {
        return data.parallelStream().reduce(5, Integer::sum);
    }
    public Integer attach4(List<Integer> data) {
        return data.stream().reduce(Integer::sum).get() + 5;
    }

    public Integer attach5(List<Integer> data) {
        return data.parallelStream().reduce((m,n)->m+n).orElse(5) + 5;
    }

    public static void main(String[] args) {
        ReduceQuiz q = new ReduceQuiz();
        var list = List.of("Table", "Chair");
        String x = q.attach1(list);
        String y = q.attach2(list);
        System.out.println(x + " " + y);

        var integers = List.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println("sum is "+q.attach3(integers));
        System.out.println("sum is "+q.attach4(integers));
        System.out.println("sum is "+q.attach5(integers));
    }
}

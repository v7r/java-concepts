package org.example.streams;

import org.example.enumsandswitch.Desig;

import java.util.Arrays;
import java.util.List;

import static org.example.enumsandswitch.Desig.СЕO;

public class DropWhileStream {


    public static void main(String[] args) {
        Arrays.stream(Desig.values()).dropWhile(s -> {
            System.out.println(s + " isEquals " + Desig.CMO + " ? " + s.equals(Desig.CMO));
            return s.equals(Desig.CMO);
        }).forEach(e -> System.out.println("Desig: "+e)); // Unexpected result because the list is not ordered.

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9);
        numbers.stream().dropWhile(n -> n < 4).forEach(System.out::println);

        switch (Desig.valueOf("CMO")) {
            case СЕO -> System.out.println("Executive");
            case CMO -> System.out.println("Marketing");
            case СTO -> System.out.println("Technology");
            case СFO -> System.out.println("Financial");
            default -> System.out.println("default");
        }


        System.out.println("CMO ordinal is "+Desig.СTO.ordinal());
    }
}

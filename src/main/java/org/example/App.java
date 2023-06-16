package org.example;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;

class StringMerger {
    public static <T> T mergeStrings(T s, T s2, BiFunction<T,T,T> bifunction) {
        return bifunction.apply(s, s2);
    }
}

class Person {
    public enum Sex {
        MALE, FEMALE;
    }

    private String name;
    private int age;
    private Sex sex;
    public Person(String name, int age, Sex sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void labelSex(GenderChecker c) {
        System.out.println(this.name+ " is "+(c.isMale(this) ? "Male" : "Female"));
    }
    public Sex getSex() {
        return this.sex;
    }
    public String getName() {
        return this.name;
    }
    public int getAge() {
        return this.age;
    }
}

@FunctionalInterface
interface GenderChecker {
    boolean isMale(Person p);
}

public class App {
    static private void title(String title) {
        System.out.println("\n\nDemo: " + title);
    }

    public static void main(String[] args) {
        int i = 10;
        int j = 0;
        int k = 0;

        j = i++;
        k = ++i;

        System.out.println("i: " + i);
        System.out.println("j: " + j);
        System.out.println("k: " + k);

        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mike");
        names.add("Joe");

        for (String name : names) {
            System.out.println("Name - " + name);
        }

        int b = 0;
        while (b < names.size()) {
            System.out.println("Names while - " + names.get(b));
            b++;
        }

        b = 0;
        do {
            System.out.println("Do while names: " + names.get(b));
            b++;
        } while (b < names.size());

        switch (names.get(0)) {
            case "John":
            case "Karen":
                System.out.println("John hit the switch case");
                break;
            case "Mike":
                System.out.println("Mike hit the switch case");
                break;
            case "Joe":
                System.out.println("Joe hit the switch case");
                break;
            default:
                System.out.println("Unknown hit the switch case");
        }


        title("stream().forEach that uses a Lambda with andThen()");
        Consumer<String> c = (name) -> System.out.print("Lambda:: Name is " + name + ". ");
        names.stream().forEach(c.andThen((name2) -> System.out.println(name2 + " is smart!")));

        //names.stream().each(n -> System.out.println("Name stream: "+n));

        title("Functional Interface by reference");
        System.out.println("Merge strings - " + StringMerger.mergeStrings("Michael", "Moose", String::concat));

        title("Lambda Usage");
        Person person = new Person("Joe Mansion", 27, Person.Sex.MALE);
        person.labelSex(p -> p.getSex() == Person.Sex.MALE);

        title("Collections pipeline filter and forEach");
        List<Person> people = new ArrayList();
        people.add(new Person("Terry Smith", 47, Person.Sex.MALE));
        people.add(new Person("Cathy Wood", 39, Person.Sex.FEMALE));
        people.add(new Person("Peter Lynch", 77, Person.Sex.MALE));
        people.add(new Person("Kamala Harris", 56, Person.Sex.FEMALE));

        people.stream()
                .filter(per -> per.getSex() == Person.Sex.FEMALE)
                .forEach(per -> System.out.println(per.getName() + " is female"));

        title("Collectors.groupingBy()");
        Map<Person.Sex, List<Person>> peopleBySex =
                people.stream().collect(Collectors.groupingBy(Person::getSex));
        peopleBySex.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " are " + e.getValue().size()));

        title("Collectors.groupingBy() and mapping");
        Map<Person.Sex, List<String>> namesBySex =
                people.stream().collect(Collectors.groupingBy(
                                Person::getSex,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toList()
                                )
                        )
                );
        namesBySex.entrySet().stream().forEach(e -> {
            //System.out.println(e.getKey()+ " are "+Arrays.toString(e.getValue().toArray()));
            System.out.println(e.getKey() + " are " + e.getValue());
        });

        title("Collectors.groupingBy() and reducing");
        Map<Person.Sex, Integer> totalAgeBySex = people.stream().collect(
                Collectors.groupingBy(
                        Person::getSex,
                        Collectors.reducing(
                                0,
                                Person::getAge,
                                Integer::sum
                        )
                )
        );
        totalAgeBySex.entrySet().stream().forEach(
                e -> System.out.println(e.getKey() + " total age " + e.getValue())
        );

        title("Flat map concept");
        List<Integer> flatMappedList;
        List<Integer> intList = Arrays.asList(new Integer[]{1,2,3});
        List<Integer> flatMapList = intList.stream().flatMap(num -> {
            return Arrays.asList(new Integer[]{num, num*2}).stream();
        }).toList();
        System.out.println("Flattened list "+flatMapList);


        title("Check if two arrays are identical");
        int listA[] = {1,2,3,4,5}, listB[] = {2,3,4,5,1,1,7};
        Set<Integer> setA = Arrays.stream(listA).boxed().collect(Collectors.toSet());
        Set<Integer> setB = Arrays.stream(listB).boxed().collect(Collectors.toSet());
        setA.forEach(setB::remove);
        System.out.print("Both the lists are "+(setB.isEmpty()?"Identical":"not Identical"));

        //CompletableFuture<Integer> future = Executors.newFixedThreadPool(1).submit(() -> 1);
    }
}

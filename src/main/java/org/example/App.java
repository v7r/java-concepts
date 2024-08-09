package org.example;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
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

        title("Sort people by name and age");
        List<Person> sortedPeople = people.stream()
                .sorted(Comparator.comparing(Person::getName).thenComparing(Person::getAge))
                .collect(Collectors.toList());
        System.out.println("Sorted people by name and age "+sortedPeople);

        title("Youngest person");
        Optional<Person> youngest = people.stream().min(Comparator.comparing(Person::getAge));
        System.out.println("Youngest person is "+youngest.get());


        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(16, Comparator.reverseOrder());
        pq.add(11);
        pq.add(5);
        pq.add(55);
        pq.add(10);
        System.out.println("Maximum Number: "+pq.remove());

        int xd = 0;
        // this demonstrates the do while with single statement with no braces.
        do
            System.out.println( ++xd + " << do-while with no braces");
            while(xd < 5);

        double d = 14.2;
        float f1 = 13.2f; // compilation error if no 'f' because the literal with decimal places are double type by default.

        boolean b1 = 5 <= 6 == 7 <= 10; // true
        boolean b2 = true == false;

        int l1 = 123___4____34__________123; // strage !! but no copilation error :)
        String s1 = """
                First line
                Another Line
                """;
        System.out.println("Multiline string: "+s1);

        // demonstrates all valid declarations
        int inta[], anotherInteger; // inta is an int array type whereas anotherInteger is int type.
        int[] inta1 = new int[10];
        int[] inta2 = new int[] {1,2,3,4,5}; // you should not specify the size of the array in the angular brackets instead the size is the number of elements initialized.
        int[] inta3 = {1,2,3,4,5}; // Another way to create and initialize array.

        String bugs[] = new String[]{"ant","wasp","bee"};
        String bugs1[] = {"ant","wasp","bee"};
        System.out.println("Array.equal() ?"+ bugs.equals(bugs1)); // false. by default Array.equals() compares object reference with == operator
        System.out.println("Arrays.equals() ?"+ Arrays.equals(bugs, bugs1)); // true ?

        var localDate = LocalDate.of(2024,05,13);
        ChronoUnit.DAYS.between(localDate, localDate); // returns zero

        LocalDate aDate = LocalDate.of(2024, 6, 13);
        Duration oneDayDuration = Duration.ofHours(24);
        Period oneDayPeriod = Period.ofDays(1);

        aDate.plus(oneDayPeriod); // adds one day to date.
        try {
            // throws java.time.temporal.UnsupportedTemporalTypeException: Unsupported unit: Hours
            // since aDate has no time component to add oneDayDuration.
            aDate.plus(oneDayDuration);
        } catch (UnsupportedTemporalTypeException te) {
            //te.printStackTrace();
        }
        String a = "";
        a += 'c'; // yieks !! it's valid
        System.out.println("String a is %s".formatted(a));

        // Question 5
        String s2 = "Hello! Java";
        System.out.print(s2.indexOf("Java"));
        s2.replace("Hello!", "Welcome!");
        System.out.print(s2.indexOf("Java"));
        StringBuilder sb1 = new StringBuilder(s2);
        System.out.print(sb1.indexOf("Java"));

        System.out.println("");

        String myStr = "Hello Java 17";
        String myTextB1k1 = """
                Hello Java 17""";
        String myTextB1k2 = """
                Hello Java 17
                """;
        System.out.print(myStr.equals(myTextB1k1)+":");                 // true
        System.out.print(myStr.equals(myTextB1k2)+":");                 // false
        System.out.print(myTextB1k1.equals(myTextB1k2)+":");            // false
        System.out.println(myTextB1k1.intern() == myTextB1k2.intern()); // false

        // #6
        String ns = "1_0";
        Integer anInt = 1_0;
        Integer sum;
        try {
            sum = 100 + Integer.parseInt(ns); // throws number format exception;
            System.out.println("1. Sum is %d".formatted(sum));
        } catch (NumberFormatException nfe) {}

        try {
            sum = 100 + new Integer(ns);
            System.out.println("2. Sum is %d".formatted(sum)); // throws nfe
        } catch (NumberFormatException nfe) {}

        try {
            sum = 100 + anInt;
            System.out.println("3. Sum is %d".formatted(sum)); // prints the sum
        } catch (NumberFormatException nfe) {}

        try {
            sum = 250;
            sum += anInt;
            System.out.println("4. Sum is %d".formatted(sum)); // prints the sum
        } catch (NumberFormatException nfe) {}

        // #7
        int i1 = 5;
        int i2 = ~i1;
        int i3 = i1 ^ i2;
        boolean bool1 = i1 < i2 & i1 > i3++;
        System.out.println(bool1 + " " + i3);
        boolean bool2 = i1 > i2 && i1 > i3++;
        System.out.println(bool2 + " " + i3);

        // question 10
        List<String> holidays = List.of("NewYear", "Valentines", "Junteenth", "Thanksgiving");
        System.out.print(holidays.stream().allMatch(h -> h.equals("Thanksgiving")));
        System.out.print(" " + holidays.stream().anyMatch(h -> h.equals("Thanksgiving")));
        System.out.print(" " + holidays.stream().noneMatch(h -> h.equals("Birthday")));
        System.out.println(" " + holidays.stream().findFirst());
    }
}

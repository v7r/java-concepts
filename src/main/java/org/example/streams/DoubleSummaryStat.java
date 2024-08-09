package org.example.streams;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class DoubleSummaryStat {
    static class Item {
        String name;
        double price;

        Item(String s, double d) {
            this.name = s;
            this.price = d;
        }
    }

    static class ElectricItem extends Item {
        ElectricItem(String name, double price) {
            super(name, price);
        }
    }

    public static void main(String[] args) {
        List<Item> p = List.of(
                new ElectricItem("CellPhone", 100),
                new ElectricItem("ToyCar", 90),
                new ElectricItem("Motor", 200),
                new ElectricItem("Fan", 300)
        );
        DoubleSummaryStatistics sts = p.stream().filter(a -> a instanceof ElectricItem)
                .collect(Collectors.summarizingDouble(a -> a.price));
        String productNames = p.stream().filter(a -> a instanceof Item)
                .collect(Collectors.mapping(p2 -> p2.name, Collectors.joining(",")));

        System.out.println("Max price %f, Item names %s".formatted(sts.getMax(), productNames));
        //System.out.println("Product Names: "+ productNames);
    }
}

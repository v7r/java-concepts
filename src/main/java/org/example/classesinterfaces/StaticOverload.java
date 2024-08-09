package org.example.classesinterfaces;

public class StaticOverload {
    static class Item {
        static String name;
        public static void display () {
            name = "vase";
            System.out.println(name);
        }
        public void display(String design) {
            this.name += name;
            System.out.println(name);
        }
    }

    public static void main(String[] args) {
        Item i = new Item();
        i.display("hey");;
    }
}

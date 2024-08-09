package org.example.record;

record Product(int id, String name) {
    // You can't define a member that is non-static.
    static int dept = 100;
    public int getDepartment() {
        return dept;
    }
}

public class RecordMember {
    public static void main(String[] args) {
        Product p = new Product(1, "Labs");
    }
}

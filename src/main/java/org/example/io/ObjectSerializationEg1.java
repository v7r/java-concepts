package org.example.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializationEg1 {
    public static void main(String[] args) {
        Product p = new Product("Cookie", 3.99f);
        System.out.println("Product before serialization "+p);
        try {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("product.ser"))) {
                out.writeObject(p);
            }
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("product.ser"))) {
                Object o = in.readObject();
                if (o instanceof Product prod) {
                    p = prod;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Serialized and then deserialized product "+p);
    }
}

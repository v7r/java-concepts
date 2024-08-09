package org.example.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Product implements Serializable {
    private static float averagePrice = 2.99f;
    private String description;
    private transient float price;

    public Product(String description, float price) {
        this.description = description;
        this.price = price;
    }

    /**
     * Pay attention to the method signature
     * Refer class documentation of Serializable interface.
     *
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        price = averagePrice;
    }

    /**
     * Pay attention to the method signature
     * Refer class documentation of Serializable interface.
     * @param out
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    @Override
    public String toString() {
        return this.description + " " + price + " " + averagePrice;
    }
}

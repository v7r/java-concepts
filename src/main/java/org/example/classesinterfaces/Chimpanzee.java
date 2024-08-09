package org.example.classesinterfaces;

import java.io.Serializable;

public class Chimpanzee implements Serializable {
    private static final long serialVersionUID = 2L;
    private transient String name;
    private transient int age = 10;
    private static char type = 'C';

    /**
     * Instance initializer block. This is invoked for every instance creation.
     *
     * Instance initializer blocks are executed in the order they appear in the class, before any constructor.
     * You can have multiple instance initializer blocks in a class.
     * They are often used for complex initialization logic that is shared across multiple constructors.
     */
    {
        this.age = 18;
    }
}

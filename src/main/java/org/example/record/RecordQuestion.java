package org.example.record;

/**
 * Java 17 Record updates
 *
 * Ref: https://docs.oracle.com/en/java/javase/17/language/records.html#GUID-6699E26F-4A9B-4393-A08B-1E47D4B2D263
 */
public class RecordQuestion {

    record Shape(int faces, int angles) {

        // This is java 17 way of creating record constructor. Notice that there are no arguments.
        public Shape {
            if (faces < 0) throw new IllegalArgumentException("Shape faces should be positive");
            if (angles < 0) throw new IllegalArgumentException("Shape angles should be positive");
        }

        // Java 17 let's you specify the static members in records and static initializers.
        static String version;
        static {
            version = "v17";
        }

        // also lets you define static methods in records.
        public static String getVersion() {
            return version;
        }

    };

    public static void main(String[] args) {
        Shape s = new Shape(2, 1);
    }

}

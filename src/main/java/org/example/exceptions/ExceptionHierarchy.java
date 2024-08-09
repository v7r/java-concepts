package org.example.exceptions;

public class ExceptionHierarchy {
    static class InventoryException extends Exception {
        public InventoryException(String s) {
            super(s);
        }
    }

    static class OutOfInventoryException extends InventoryException {
        public OutOfInventoryException(String s) {
            super(s);
        }
    }

    public static void process() throws OutOfInventoryException {
        try {
            throw new InventoryException("error");
        } catch (Exception e) {
            throw new OutOfInventoryException("Some error, root: "+e.getMessage());
        }
    }

    public static void main(String[] args) throws OutOfInventoryException {
        process();
    }
}

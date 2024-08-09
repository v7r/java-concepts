package org.example.streams;

import java.util.List;

public class SortAList {
    static class Book {
        String author, title;
        Book(String author, String title) {
            this.author = author;
            this.title = title;
        }
    }

    public static void main(String[] args) {
        // Option A: Change to List<Book> instead of List
        List books = List.of(
                new Book("A1","T1"),
                new Book("A2","T2"),
                new Book("A1","T2")
        );
        // Option B:
        //books.sort((Book a, Book b) -> a.title.compareTo(b.title));

        // Option C:
        //books.stream().sorted((Book a, Book b) -> a.title.compareTo(b.title));
    }
}

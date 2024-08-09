package org.example.modules.three;

public sealed interface SInt permits Story, Art {
    default String getTitle() {
        return "Book Title" ;
    }
}

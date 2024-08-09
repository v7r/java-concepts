package org.example.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWriteFile {
    public static void main(String[] args) throws IOException {
        char[] buffer = new char[8];
        int count = 0;
        try (FileReader in = new FileReader("in.text");
             FileWriter out = new FileWriter("out.text");
        ) {
            while((count = in.read(buffer)) != -1) {
                out.write(buffer);
            }
        }
    }
}

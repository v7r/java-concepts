package org.example.locales;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalQuiz {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Locale currentLocale = new Locale.Builder().setLanguage("en").build();

        // Throws error
        // Expects the following files
        // Labels.properties_en_US
        // Labels.properties_en
        // Labels.properties
        ResourceBundle labels = ResourceBundle.getBundle("Labels_en_US.properties", currentLocale);
        System.out.println(labels.getString("user"));
    }
}

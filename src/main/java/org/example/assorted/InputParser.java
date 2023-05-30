package org.example.assorted;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Section {
    private int id;
    private int sectionNumber;
    private String name;
    private int errors;
    private int warnings;

    public Section(int id, int sectionNumber, String name, int errors, int warnings) {
        this.id = id;
        this.sectionNumber = sectionNumber;
        this.name = name;
        this.errors = errors;
        this.warnings = warnings;
    }

    public int getId() {
        return id;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public String getName() {
        return name;
    }

    public int getErrors() {
        return errors;
    }

    public int getWarnings() {
        return warnings;
    }
}

public class InputParser {
    public static void main(String[] args) {
        String input = "Section 1\n" +
                "Name: Paris -\n" +
                "ID: 4\n" +
                "This section has 3 errors, and 2 warnings\n" +
                "Section 5\n" +
                "Name: New York - ID: 1\n" +
                "This section has 5 errors\n" +
                "Section 11\n" +
                "Name: Tokvo - ID: 8\n" +
                "Section 16\n" +
                "Name: Hong Kong - ID: 6\n" +
                "This section has 1 warning";

        List<Section> sections = parseInput(input);
        generateOutput(sections);
    }

    public static List<Section> parseInput(String input) {
        List<Section> sections = new ArrayList<>();

        // Regex patterns to extract information from each section
        Pattern sectionPattern = Pattern.compile("Section (\\d+)");
        Pattern namePattern = Pattern.compile("Name: (.*?) -");
        Pattern idPattern = Pattern.compile("ID: (\\d+)");
        Pattern errorsPattern = Pattern.compile("This section has (\\d+) error");
        Pattern warningsPattern = Pattern.compile("This section has (\\d+) warning");

        // Split input into lines
        String[] lines = input.split("\n");

        // Process each line
        int sectionNumber = 0;
        int id = 0;
        String name = "";
        int errors = 0;
        int warnings = 0;

        for (String line : lines) {
            Matcher sectionMatcher = sectionPattern.matcher(line);
            Matcher nameMatcher = namePattern.matcher(line);
            Matcher idMatcher = idPattern.matcher(line);
            Matcher errorsMatcher = errorsPattern.matcher(line);
            Matcher warningsMatcher = warningsPattern.matcher(line);

            if (sectionMatcher.find()) {
                sectionNumber = Integer.parseInt(sectionMatcher.group(1));
            } else if (nameMatcher.find()) {
                name = nameMatcher.group(1).trim();
            } else if (idMatcher.find()) {
                id = Integer.parseInt(idMatcher.group(1));
            } else if (errorsMatcher.find()) {
                errors = Integer.parseInt(errorsMatcher.group(1));
            } else if (warningsMatcher.find()) {
                warnings = Integer.parseInt(warningsMatcher.group(1));

                // Create a new section object and add it to the list
                Section section = new Section(id, sectionNumber, name, errors, warnings);
                sections.add(section);

                // Reset the variables for the next section
                sectionNumber = 0;
                id = 0;
                name = "";
                errors = 0;
                warnings = 0;
            }
        }

        return sections;
    }

    public static void generateOutput(List<Section> sections) {
        int totalErrors = 0;
        int totalWarnings = 0;

        for (Section section : sections) {
            System.out.printf("%d - %d - %s - %d - %d%n",
                    section.getId(), section.getSectionNumber(),
                    section.getName(), section.getErrors(), section.getWarnings());

            totalErrors += section.getErrors();
            totalWarnings += section.getWarnings();
        }

        double avgErrors = totalErrors / (double) sections.size();
        double avgWarnings = totalWarnings / (double) sections.size();

        System.out.printf("Average Errors: %.2f%n", avgErrors);
        System.out.printf("Average Warnings: %.2f%n", avgWarnings);
    }
}


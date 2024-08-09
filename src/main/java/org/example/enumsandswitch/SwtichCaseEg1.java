package org.example.enumsandswitch;

public class SwtichCaseEg1 {
    public static void main(String[] args) {
        System.out.println("Ordinal of Sunny "+ Forecast.SUNNY.ordinal());
        System.out.println(Forecast.valueOf("cloudy".toUpperCase()));
    }
}

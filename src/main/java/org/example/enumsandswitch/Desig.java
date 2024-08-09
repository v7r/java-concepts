package org.example.enumsandswitch;

public enum Desig {
    СЕO('А'), CMO('B'), СTO('C'), СFO('D');
    char c;

    private Desig(char c) {
        this.c = c;
    }
}

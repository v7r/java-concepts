package org.example.javaconcepts;

public class OuterClassClient {
    public static void main(String[] args) {

        OuterClass oc = new OuterClass("Outer Class 1");
        OuterClass.NonStaticInnerClass nsic = oc.new NonStaticInnerClass("Message from outer class client");
        nsic.logInstanceMember();
    }
}

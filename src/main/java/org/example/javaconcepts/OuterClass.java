package org.example.javaconcepts;

public class OuterClass {

    public static class StaticInnerClass {
        private String instanceMember;
        public StaticInnerClass(String instanceMember) {
            this.instanceMember = instanceMember;
        }

        public void logInstanceMember() {
            System.out.println("StaticInnerClass instance member: "+this.instanceMember);
            outerMessageStatic();
        }
    }

    public class NonStaticInnerClass {
        private String instanceMember;
        public NonStaticInnerClass(String instanceMember) {
            this.instanceMember = instanceMember;
        }

        public void logInstanceMember() {
            System.out.println("NonStaticInnerClass instance member: "+this.instanceMember+", my parent is: "+myParentName());
            outerMessageNonStatic();
            outerMessageStatic();
        }

        public String myParentName() {
            return myName;
        }
    }


    private String myName;
    private StaticInnerClass staticInnerInstance;
    private NonStaticInnerClass nonStaticInnerInstance;

    public OuterClass(String name) {
        this.myName = name;
        this.staticInnerInstance = new StaticInnerClass("Created from outer");
        this.nonStaticInnerInstance = new NonStaticInnerClass("Created from outer");
    }

    public void logInstanceMembers() {
        this.staticInnerInstance.logInstanceMember();
        this.nonStaticInnerInstance.logInstanceMember();
    }

    public void outerMessageNonStatic() {
        System.out.println("This is from outer class non static");
    }

    public static void outerMessageStatic() {
        System.out.println("This is from outer class static");
    }
}

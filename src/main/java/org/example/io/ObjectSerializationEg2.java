package org.example.io;

import java.io.*;

/**
 * What is the output of the following ?
 *
 */
public class ObjectSerializationEg2 {
    static public class Software implements Serializable {
        private String title;
        public Software(String title) {
            this. title = title;
            System. out.print ("Software ");
        }

        public String toString() {
            return this.title;
        }
    }

    static public class Game extends Software {
        private int players;
        public Game(String title, int players) {
            super (title);
            this.players = players;
            System.out.print ("Game ");
        }
        public String toString() {
            return super.toString() + " " + players;
        }
    }

    public static void main(String[] args) {
        Software s = new Game("Chess", 2);

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("game.ser"))) {
            out. writeObject(s) ;
        } catch (Exception e) {
            System.out.println("write error");
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("game.ser"))) {
            s = (Software) in.readObject();
        } catch (Exception e) {
            System.out.println("read error");
        }
        System.out.println(s);
    }
}

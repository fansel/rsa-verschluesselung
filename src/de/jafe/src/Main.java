package de.jafe.src;
import de.oop2023.util.UserInterface;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Die Klasse Main ermöglicht das Generieren von Schlüsselpaaren und das Verschlüsseln und Entschlüsseln von Dateien.
 * @author Jakob
 */
public class Main {
    /**
     * Hauptmethode des Programms.
     * @param args Kommandozeilenargumente
     */
    public static void main(String[] args) {

        while (true) {
            int choice = UserInterface.in.requestInt("Wollen Sie ein KeyPair generieren(1), eine Datei verschlüsseln(2) oder entschlüsseln(" +
                    "3) oder das Programm beenden(4)?");

            if (choice == 1) {
                int choice2 = UserInterface.in.requestInt("Wollen Sie die Primzahlen selbst eingeben(1) oder sollen sie generiert werden(2)?");
                BigInteger p = BigInteger.valueOf(1);
                BigInteger q = BigInteger.valueOf(1);
                if (choice2 == 1){

                    while (!Schluesselgenerator.isPrime(p)) {
                        p = UserInterface.in.requestBigInt("Geben Sie die Primzahl P ein");
                        if (!Schluesselgenerator.isPrime(p))
                            System.out.println(p + " ist keine Primzahl");
                    }

                    while (!Schluesselgenerator.isPrime(q)) {
                        q = UserInterface.in.requestBigInt("Geben Sie die Primzahl Q ein");
                        if (!Schluesselgenerator.isPrime(q))
                            System.out.println(q + " ist keine Primzahl");
                    }


                }
                else if (choice2 == 2){
                    p = Schluesselgenerator.generateProbablePrime();
                    q = Schluesselgenerator.generateProbablePrime();

                }

                Schluesselgenerator sg = new Schluesselgenerator(p, q);
                sg.generateKeyPair();
                System.out.println("Ihr öffentlicher Schlüssel ist: " + sg.printPublicKey());
                System.out.println("Ihr privater Schlüssel ist: " + sg.printPrivateKey());
            }
            else if (choice == 2) {
                String dateipfad = UserInterface.in.requestString("Geben Sie den Dateipfad zur Textdatei ein, die Sie verschlüsseln wollen:");
                BigInteger e = UserInterface.in.requestBigInt("Geben Sie e des öffentlichen Schlüssel ein:");
                BigInteger g = UserInterface.in.requestBigInt("Geben Sie g des öffentlichen Schlüssel ein:");
                Encoder en = new Encoder(e, g);
                en.encode(dateipfad);
            }


            else if (choice == 3) {
                String dateipfad =UserInterface.in.requestString("Geben Sie den Dateipfad zur Textdatei ein, die Sie entschlüsseln wollen:");
                BigInteger e = UserInterface.in.requestBigInt("Geben Sie d des privaten Schlüssel ein:");
                BigInteger g = UserInterface.in.requestBigInt("Geben Sie g des privaten Schlüssel ein:");
                Decoder de = new Decoder(e, g);
                de.decodeFile(dateipfad);
            }

            else if (choice == 4) {
                System.exit(1);
            }
        }
    }
}

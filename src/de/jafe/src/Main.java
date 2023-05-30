package de.jafe.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Schluesselgenerator sg = new Schluesselgenerator(35158139,93786151);
        sg.generateKeyPair();
        Encoder en = new Encoder(sg);
        Decoder dc = new Decoder(sg);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String filePath = scanner.nextLine();
        en.readFile(filePath);

        System.out.println("Do you want to encode or decode? (e/d): ");
        String choice = scanner.nextLine();

        if (choice.equals("e")) {
            en.encode();
            System.out.println("Encoded text: ");
            en.printCharValues();
        } else if (choice.equals("d")) {
            System.out.println("Decoded text: ");
            dc.printDecoded(en.getCharValues());
        } else {
            System.out.println("Invalid choice.");
        }
    }
}

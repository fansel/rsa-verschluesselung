package de.jafe.src;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Encoder {
    private List<BigInteger> charValues;
    private Schluesselgenerator schluesselgenerator;

    public Encoder(Schluesselgenerator schluesselgenerator) {
        this.schluesselgenerator = schluesselgenerator;
        charValues = new ArrayList<>();
    }

    public void readFile(String fileName) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileName)));
            for (char c : content.toCharArray()) {
                charValues.add(BigInteger.valueOf(c));
            }
        } catch (IOException e) {
            System.out.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }

    public List<BigInteger> getCharValues() {
        return charValues;
    }

    public void printCharValues() {
        for (BigInteger value : charValues) {
            System.out.print(value+" ");
        }
        System.out.println();
    }

    public void encodeText() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben Sie den zu decodierenden Text ein:");
        String input = scanner.nextLine();
        for (char c : input.toCharArray()) {
            charValues.add(BigInteger.valueOf(c));
        }
    }

    public void encode() {
        charValues=charValues.stream()
                .map(value -> value.modPow(schluesselgenerator.getE(), schluesselgenerator.getG()))
                .collect(Collectors.toList());
    }

}

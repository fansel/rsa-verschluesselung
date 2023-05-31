package de.jafe.src;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Die Klasse Encoder ermöglicht das Verschlüsseln von Dateien.
 * @author Jakob
 */
public class Encoder {
    private List<BigInteger> charValues;
    private BigInteger e;
    private BigInteger g;

    /**
     * Konstruktor für die Klasse Encoder.
     * @param e der Wert von e
     * @param g der Wert von g
     */
    public Encoder(BigInteger e, BigInteger g) {
        this.e = e;
        this.g = g;
    }

    /**
     * Liest eine Datei ein und speichert die Zeichenwerte in einer Liste.
     * @param dateipfad der Name der Datei
     */
    private void readFile(String dateipfad) {
        //check if file exists
        Path path = Paths.get(dateipfad);
        if (!Files.exists(path)) {
            System.out.println("Diese Datei existiert nicht!");
            return;
        }
        try {
            charValues = Files.readAllLines(path).stream()
                    .map(line -> line + System.lineSeparator()) // add new line character
                    .map(line -> line.toCharArray())
                    .flatMap(chars -> {
                        List<BigInteger> charValues = new ArrayList<>();
                        for (char c : chars) {
                            charValues.add(BigInteger.valueOf(c));
                        }
                        return charValues.stream();
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gibt die Liste der Zeichenwerte zurück.
     * @return die Liste der Zeichenwerte
     */
    public List<BigInteger> getCharValues() {
        return charValues;
    }
    /**
     * Verschlüsselt eine Datei.
     * @param dateipfad der Name der Datei
     */
    public void encode(String dateipfad) {
        readFile(dateipfad);
        if (charValues == null || charValues.isEmpty()) {
            System.out.println("Die Datei konnte nicht eingelesen werden!");
            return;
        }
        charValues = charValues.stream()
                .map(value -> value.modPow(e, g))
                .collect(Collectors.toList());
        String fileName = dateipfad.substring(0, dateipfad.lastIndexOf('.')) + ".enc";
        writeToFile(fileName);
    }

    /**
     * Schreibt die verschlüsselten Werte in eine Datei.
     * @param fileName der Name der Datei
     */
    private void writeToFile(String fileName) {
        try {
            Files.write(Paths.get(fileName), charValues.stream()
                    .map(BigInteger::toString)
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

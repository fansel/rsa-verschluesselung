package de.jafe.src;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Die Klasse Decoder ermöglicht das Entschlüsseln von Dateien.
 * @author Felix
 */
public class Decoder {
    private BigInteger d;
    private BigInteger g;

    /**
     * Konstruktor für die Klasse Decoder.
     * @param d der Wert von d
     * @param g der Wert von g
     */
    public Decoder(BigInteger d, BigInteger g) {
        this.d = d;
        this.g = g;
    }

    /**
     * Gibt die entschlüsselten Werte aus.
     * @param entschluesseln die Liste der entschlüsselten Werte
     */
    public void printDecoded(List<BigInteger> entschluesseln) {
        for (BigInteger value : entschluesseln) {
            if (value.intValue() == 10) { // check for new line character
                System.out.println();
            } else {
                System.out.print((char) value.intValue());
            }
        }
        System.out.println();
    }

    /**
     * Entschlüsselt eine Datei.
     * @param path der Pfad zur Datei
     */
    public void decodeFile(String path) {
        List<BigInteger> encodedValues = new ArrayList<>();
        List<BigInteger> decodedValues = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            encodedValues = lines
                    .map(line -> line.split(" "))
                    .flatMap(chars -> Arrays.stream(chars).map(BigInteger::new))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        decodedValues = encodedValues.stream()
                .map(value -> value.modPow(d, g))
                .collect(Collectors.toList());
        printDecoded(decodedValues);
    }

}

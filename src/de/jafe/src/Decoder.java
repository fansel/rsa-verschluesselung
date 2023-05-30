package de.jafe.src;

import java.math.BigInteger;
import java.util.List;

public class Decoder {
    private Schluesselgenerator schluesselgenerator;

    public Decoder(Schluesselgenerator schluesselgenerator) {
        this.schluesselgenerator = schluesselgenerator;
    }

    public void printDecoded(List<BigInteger> entschluesseln) {
        for (BigInteger bigInteger : entschluesseln) {
            BigInteger decodedValue = bigInteger.modPow(schluesselgenerator.getD(), schluesselgenerator.getG());
            int i = decodedValue.intValue();
            System.out.print((char) i);
        }
    }
}

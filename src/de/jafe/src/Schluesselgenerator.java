package de.jafe.src;
import java.math.*;
import java.util.ArrayList;

/**
 * Die Klasse Schluesselgenerator ermöglicht das Generieren von Schlüsselpaaren.
 * @author Felix
 */
public class Schluesselgenerator {
    private BigInteger p;
    private BigInteger q;
    private BigInteger g;
    private BigInteger e;
    private BigInteger d;
    private BigInteger phi_g;

    /**
     * Konstruktor für die Klasse Schluesselgenerator.
     * @param p der Wert von p
     * @param q der Wert von q
     */
    public Schluesselgenerator(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        this.g = this.p.multiply(this.q);
        this.phi_g = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE));
    }

    /**
     * Generiert ein Schlüsselpaar.
     */
    public void generateKeyPair() {
        this.e = BigInteger.valueOf(2);
        while (!this.e.gcd(this.phi_g).equals(BigInteger.ONE)) {
            this.e = this.e.add(BigInteger.ONE);
        }
        this.d = this.e.modInverse(this.phi_g);
    }


    /**
     * Überprüft, ob eine Zahl eine Primzahl ist.
     * @param number die zu überprüfende Zahl
     * @return true, wenn die Zahl eine Primzahl ist, sonst false
     */
    public static boolean isPrime(BigInteger number) {
        if (number.equals(BigInteger.ONE) || number.equals(BigInteger.ZERO)) {
            return false;
        }
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(number.sqrt()) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Gibt den öffentlichen Schlüssel als String zurück.
     * @return der öffentliche Schlüssel als String
     */
    public String printPublicKey(){
        return "{" + this.e + "," + this.g+ "}";
    }

    /**
     * Gibt den privaten Schlüssel als String zurück.
     * @return der private Schlüssel als String
     */
    public String printPrivateKey(){
        return "{" + this.d + "," + this.g+ "}";
    }
}

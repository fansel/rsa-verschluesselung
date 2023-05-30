package de.jafe.src;
import java.math.*;

public class Schluesselgenerator {
    private BigInteger p;
    private BigInteger q;
    private BigInteger g;
    private BigInteger e;
    private BigInteger d;
    private BigInteger phi_g;

    public Schluesselgenerator(int p, int q) {
        this.p = BigInteger.valueOf(p);
        this.q = BigInteger.valueOf(q);
        this.g = this.p.multiply(this.q);
        this.phi_g = this.p.subtract(BigInteger.ONE).multiply(this.q.subtract(BigInteger.ONE));
    }

    public void generateKeyPair() {
        this.e = BigInteger.valueOf(2);
        while (!this.e.gcd(this.phi_g).equals(BigInteger.ONE)) {
            this.e = this.e.add(BigInteger.ONE);
        }
        this.d = this.e.modInverse(this.phi_g);
    }

    public BigInteger getE() {
        return e;
    }

    public BigInteger getD() {
        return d;
    }

    public BigInteger getG() {
        return g;
    }
}

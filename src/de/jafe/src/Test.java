package de.jafe.src;

public class Test {

    import java.math.BigInteger;

    public class RSAKeyGenerator {
        public static void main(String[] args) {
            BigInteger p = new BigInteger("61"); // Primzahl p
            BigInteger q = new BigInteger("53"); // Primzahl q

            BigInteger n = p.multiply(q); // n = p * q
            BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE)); // φ(n) = (p - 1) * (q - 1)

            BigInteger e = calculateE(phi); // Öffentlicher Schlüssel e
            BigInteger d = calculateD(e, phi); // Privater Schlüssel d

            System.out.println("Öffentlicher Schlüssel (e): " + e);
            System.out.println("Privater Schlüssel (d): " + d);
        }

        public static BigInteger calculateE(BigInteger phi) {
            BigInteger e = BigInteger.valueOf(2); // Starte mit e = 2

            while (e.compareTo(phi) < 0) {
                if (phi.gcd(e).equals(BigInteger.ONE)) {
                    break;
                }
                e = e.add(BigInteger.ONE);
            }

            return e;
        }

        public static BigInteger calculateD(BigInteger e, BigInteger phi) {
            BigInteger d = e.modInverse(phi); // Verwende die Moduloinverse von e und φ(n)

            return d;
        }
    }

}

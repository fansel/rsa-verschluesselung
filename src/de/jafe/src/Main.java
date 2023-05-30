package de.jafe.src;

public class Main {

    public static void main(String[] args) {
        Schluesselgenerator sg = new Schluesselgenerator(35158139,93786151);
        sg.generateKeyPair();
        Encoder en = new Encoder(sg);
        en.encodeText();
        en.printCharValues();
        en.encode();
        en.printCharValues();
        System.out.println(sg.getD()+" G"+sg.getG());
        Decoder dc = new Decoder(sg);
        dc.printDecoded(en.getCharValues());


    }
}

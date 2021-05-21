package cz.muni.fi.pb162.hw01.impl.ciphers;


/**
 * Application class represents the command line interface of this application.
 *
 * @author Pavel Shishkin
 * */

public class Vigenere implements Cipher{
    private String vigenereKey;
    /**
     * Application class represents the command line interface of this application.

     *
     * @param vigenereKey is vigenereKey
     */


    public Vigenere(String vigenereKey){
        this.vigenereKey = vigenereKey;
    }



    @Override
    public String encrypt(String plainText){

        // I'm still writing code like I'm programming in python, sorry

        char[] chars = plainText.toCharArray(), vigenerechars = vigenereKey.toCharArray();
        int indexvin, index, len = vigenereKey.length();
        StringBuilder word = new StringBuilder();
        for (int i=0; i < plainText.length(); i++){
            if (Character.isAlphabetic(chars[i])||Character.isDigit(chars[i])) {
                if (len - 1 < i) {
                    indexvin = i % len;
                } else {
                    indexvin = i;
                }
                index = ALPHABET.indexOf(vigenerechars[indexvin]);

                // I do not know how correct this is, but
                // I decided to use the ready-made Caesar encoding to work with this

                Ceasar ceasar = new Ceasar(index);
                word.append(ceasar.encrypt(Character.toString(chars[i])).charAt(0));
            }else {
                word.append(chars[i]);
            }
        }
        return word.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        char[] vigenerechars = vigenereKey.toCharArray();
        int len = ALPHABET.length();
        StringBuilder word = new StringBuilder();

        // I just mirror each character of the key relative
        // to the alphabet so that the encoding algorithm can decode it
        // I think this solution overrides my terrible Morse code implementation

        for (int i=0; i < vigenereKey.length(); i++){
            word.append(ALPHABET.charAt(len-ALPHABET.indexOf(vigenerechars[i])));
        }
        vigenereKey = word.toString();
        return encrypt(cipherText);
    }

}
package cz.muni.fi.pb162.hw01.impl.ciphers;


/**
 * Application class represents the command line interface of this application.
 *
 * @author Pavel Shishkin
 */

public class Ceasar implements Cipher{
    private final int shift;
    /**
     * Application class represents the command line interface of this application.

     *
     * @param shift  is shift
     */


    public Ceasar(int shift){
        this.shift = shift;
    }



    @Override
    public String encrypt(String plainText){
        char[] chars = plainText.toCharArray();
        StringBuilder word = new StringBuilder();
        int index;
        int newindex;
        int len = ALPHABET.length();
        for (int i=0; i < plainText.length(); i++){
            if (Character.isAlphabetic(chars[i])||Character.isDigit(chars[i])) {
                index = ALPHABET.indexOf(chars[i]);

                newindex = index + shift;

                if (newindex > len - 1) {
                    newindex = newindex % len;
                }

                while (newindex < 0) {
                    newindex = newindex + len;
                }

                word.append(ALPHABET.charAt(newindex));
            }else {
                word.append(chars[i]);
            }
        }
        return word.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        // It was easy
        Ceasar ceasar = new Ceasar(-shift);
        return ceasar.encrypt(cipherText);
    }

}

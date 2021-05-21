package cz.muni.fi.pb162.hw01.impl.ciphers;


import java.util.Arrays;

/**
 * Application class represents the command line interface of this application.
 *
 * @author Pavel Shishkin
 */

public class Morse implements Cipher{
    static final String[] MORSE = {"|", ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
            "-.-", ".-..", "--", "-.", ".--.", "---", "--.-", ".-.", "...", "-",
            "..-", "...-", ".--", "-..-", "-.--", "--..", "----", ".---", "..---", "...--",
            "....-", "....", "-....", "--...", "---..", "----.", ".-.-.-", "--..--", "..--..", "..--."};
    static final String WEIRD = " ABCDEFGHIJKLMNPOQRSTUVWXYZ0123456789.,?!";

    /**
     *
     *
     *
     */


    public Morse(){

    }



    @Override
    public String encrypt(String plainText){
        char[] chars = plainText.toCharArray();
        StringBuilder word = new StringBuilder();
        int index;
        for (int i=0; i < plainText.length(); i++){
            char c = Character.toUpperCase(chars[i]);
            index = WEIRD.indexOf(c);
            String x = MORSE[index];
            word.append(x);
            if(!x.equals("|")){
                word.append("|");
            }
        }

        return word.toString();
    }

    @Override
    public String decrypt(String cipherText) {

        // Cringe

        String[] strings = cipherText.split("\\|");
        StringBuilder word = new StringBuilder();
        int index;
        char prev = '0';
        boolean check = false;
        for (String string : strings) {
            if (string.equals("")){
                word.append(' ');
                if (prev == '.' || prev == '?' || prev == '!'){
                    check = false;
                }
            }else {
                index = Arrays.asList(MORSE).indexOf(string);
                char x = WEIRD.charAt(index);
                if (check) {
                    x = Character.toLowerCase(x);
                }
                word.append(x);
                check = true;
                prev = x;
            }

        }

        return word.toString();
    }

}

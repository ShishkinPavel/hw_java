package cz.muni.fi.pb162.hw01.impl;

import com.beust.jcommander.Parameter;
import cz.muni.fi.pb162.hw01.CipherType;
import cz.muni.fi.pb162.hw01.Operation;
import cz.muni.fi.pb162.hw01.cmd.CipherTypeConverter;
import cz.muni.fi.pb162.hw01.cmd.CommandLine;
import cz.muni.fi.pb162.hw01.cmd.OperationConverter;
import cz.muni.fi.pb162.hw01.impl.ciphers.Ceasar;

import cz.muni.fi.pb162.hw01.impl.ciphers.Morse;
import cz.muni.fi.pb162.hw01.impl.ciphers.Vigenere;

/**
 * Application class represents the command line interface of this application.
 *
 * You are expected to implement  the  {@link Application#run()} method
 *
 * @author jcechace
 */
public class Application {

    @Parameter(names = {"--cipher", "-c"}, converter = CipherTypeConverter.class)
    private CipherType cipherType = CipherType.MORSE_CODE;

    @Parameter(names = {"--operation", "-o"}, required = true, converter = OperationConverter.class)
    private Operation operation;

    @Parameter(names = {"--text", "-t"}, required = true)
    private String text;

    @Parameter(names = {"--shift"})
    private int caesarShift;

    @Parameter(names = {"--key"})
    private String vigenereKey;

    @Parameter(names = "--help", help = true)
    private boolean showUsage = false;

    /**
     * Application entry point
     *
     * @param args command line arguments of the application
     */
    public static void main(String[] args) {
        Application app = new Application();

        CommandLine cli = new CommandLine(app);
        cli.parseArguments(args);

        if (app.showUsage) {
            cli.showUsage();
        } else {
            app.run();
        }
    }

    /**
     * Application runtime logic
     */
    private void run() {
        if (cipherType == CipherType.CAESAR){
                Ceasar ceasar = new Ceasar(caesarShift);
                switch (operation){
                    case ENCRYPT:
                        System.out.println(ceasar.encrypt(text));
                        break;
                    case DECRYPT:
                        System.out.println(ceasar.decrypt(text));
                        break;
                    default:
                        break;
                }
        }
            if (cipherType == CipherType.VIGENERE) {
                Vigenere vigenere = new Vigenere(vigenereKey);
                switch (operation) {
                    case ENCRYPT:
                        System.out.println(vigenere.encrypt(text));
                        break;
                    case DECRYPT:
                        System.out.println(vigenere.decrypt(text));
                        break;
                    default:
                        break;
                }
            }
            if (cipherType == CipherType.MORSE_CODE) {
                Morse morse = new Morse();
                switch (operation) {
                    case ENCRYPT:
                        System.out.println(morse.encrypt(text));
                        break;
                    case DECRYPT:
                        System.out.println(morse.decrypt(text));
                        break;
                    default:
                        break;
                }
            }
        }
    }

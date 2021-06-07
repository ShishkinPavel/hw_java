package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvParser;
import java.nio.charset.Charset;


/**
 * Factory class for CSV processing
 */
public final class CsvToolkit{


    private CsvToolkit() {
        // intentionally private to prevent instantiation
    }

    /**
     * Creates instance of {@link CsvParser} with default delimiter and charset
     *
     * @return parser
     */
    public static CsvParser parser() {
        return new Parser(CsvParser.DEFAULT_DELIMITER, CsvParser.DEFAULT_CHARSET);
    }

    /**
     * Creates instance of {@link CsvParser} with given delimiter
     *
     * @param delimiter value delimiter
     * @param charset   character encoding
     * @return parser
     */
    public static CsvParser parser(String delimiter, Charset charset) {
        return new Parser(delimiter, charset);
    }

}
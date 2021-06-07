package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvParser;
import cz.muni.fi.pb162.hw03.csv.CsvReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Pavel Shishkin
 */
public final class Parser implements CsvParser {
    private final String delimiter;
    private final Charset charset;

    /**
     * @param delimiter is delimiter
     * @param charset is charset
     */
    public Parser(String delimiter, Charset charset) {
        this.delimiter = delimiter;
        this.charset = charset;
    }


    @Override
    public CsvReader<List<String>> open(Path path) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, charset);
        return new CsvReaderList(this.delimiter, reader);
    }

    @Override
    public CsvReader<List<String>> open(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        return new CsvReaderList(this.delimiter, reader);

    }

    @Override
    public CsvReader<Map<String, String>> openWithHeader(Path path) throws IOException {
        BufferedReader reader = Files.newBufferedReader(path, charset);
        return new CsvReaderMap(this.delimiter, reader);
    }

    @Override
    public CsvReader<Map<String, String>> openWithHeader(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        return new CsvReaderMap(this.delimiter, reader);

    }

    @Override
    public List<List<String>> readAll(Path path) throws IOException {
        List<List<String>> result = new ArrayList<>();
        this.open(path).forEach(result::add);
        return result;
    }

    @Override
    public List<Map<String, String>> readAllWithHeader(Path path) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();
        this.openWithHeader(path).forEach(result::add);
        return result;
    }
}

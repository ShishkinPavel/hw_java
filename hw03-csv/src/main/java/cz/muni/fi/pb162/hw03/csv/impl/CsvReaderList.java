package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;


/**
 * @author Pavel Shishkin
 */

public class CsvReaderList implements CsvReader<List<String>> {
    private final String delimiter;
    private final BufferedReader bufferedReader;


    /**
     * @param delimiter is delimiter
     * @param bufferedReader is br
     */

    public CsvReaderList(String delimiter, BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
        this.delimiter = delimiter;
    }

    @Override
    public List<String> read() throws IOException {
        String line = this.bufferedReader.readLine();
        if( line != null) {
            String[] actualLine = line.split(this.delimiter);
            List<String> result = new ArrayList<>();
            for (String s: actualLine) {
                result.add(s.trim());
            }
            return result;
        }
        return null;
    }

    @Override
    public void forEach(Consumer<List<String>> consumer) throws IOException {
        List<String> data = this.read();
        while(data != null) {
            consumer.accept(data);
            data = this.read();
        }
    }

    @Override
    public void close() throws IOException {
        this.bufferedReader.close();
    }

}

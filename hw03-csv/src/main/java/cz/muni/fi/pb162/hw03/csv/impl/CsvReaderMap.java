package cz.muni.fi.pb162.hw03.csv.impl;

import cz.muni.fi.pb162.hw03.csv.CsvReader;
import cz.muni.fi.pb162.hw03.csv.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @author Pavel Shishkin
 */

public class CsvReaderMap implements CsvReader<Map<String, String>> {
    private final String delimiter;
    private final BufferedReader bufferedReader;
    private final List<String> header;


    /**
     * @param delimiter is delimiter
     * @param bufferedReader is bf
     * @exception IOException for wrong IO
     */
    public CsvReaderMap(String delimiter, BufferedReader bufferedReader) throws IOException {
        this.delimiter = delimiter;
        this.bufferedReader = bufferedReader;
        this.header = new CsvReaderList(delimiter, bufferedReader).read();
    }


    /**
     * @exception IOException for wrong IO
     */
    @Override
    public Map<String, String> read() throws IOException {
        String line = this.bufferedReader.readLine();
        if(line != null) {
            Map<String, String> result = new HashMap<>();
            String[] actualLine = line.split(this.delimiter);
            if(actualLine.length != this.header.size()) {
                throw new IOException(Messages.INVALID_FORMAT);
            }
            for (int j = 0; j < this.header.size(); j++) {
                result.put(this.header.get(j).trim(), actualLine[j].trim());
            }
            return result;
        }
        return null;
    }



    @Override
    public void forEach(Consumer<Map<String, String>> consumer) throws IOException {
        Map<String, String> data = this.read();
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

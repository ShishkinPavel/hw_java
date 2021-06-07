package cz.muni.fi.pb162.hw03.csv.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.ArrayList;
/**
 * @author Pavel Shishkin
 */
public class FilerTheCreator {

    /**
     * @param is is InputStream
     * @param charset is charset
     * @return list of lines
     */
    public List<String> filerTheCreator(InputStream is, Charset charset){
        List<String> xx = new ArrayList<>();
        String line;
        BufferedReader r = new BufferedReader(new InputStreamReader(is, charset));
        try {
            while ((line = r.readLine()) != null) {
                xx.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return xx;
    }
}

package cz.muni.fi.pb162.hw02.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Shishkin
 */

public class Sizes {

    private final List<String> result = new ArrayList<>();
    private List<String> file;
    /**
     * @param file is file
     */
    public Sizes(List<String> file){
        for (String line : file) {
            result.add(line.length() + ": " + line);
        }
    }

    /**
     * @return modified file
     */
    public List<String> getResult(){
        return this.result;
    }
}

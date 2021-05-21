package cz.muni.fi.pb162.hw02.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Pavel Shishkin
 */

public class Duplicates {
    private final List<String> result = new ArrayList<>();
    private List<String> file;

    /**
     * @param file is file
     */
    public Duplicates (List<String> file){
        for (String s: file) {
            if (Collections.frequency(file, s) > 1 && !result.contains(s)){
                result.add(s);
            }
        }
    }

    /**
     * @return modified file
     */
    public List<String> getResult(){
        return this.result;
    }

}

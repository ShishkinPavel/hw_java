package cz.muni.fi.pb162.hw02.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Shishkin
 */
public class Unique {

    private final List<String> result = new ArrayList<>();
    private List<String> file;

    /**
     * @param file is file
     */
    public Unique(List<String> file){
        for (String s: file) {
            if (!result.contains(s)){
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

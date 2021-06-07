package cz.muni.fi.pb162.hw03.app.impl;

import cz.muni.fi.pb162.hw03.app.ElementLibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author Pavel Shishkin
 */
public class ElementCreator implements ElementLibrary {
    private final List<List<String>> lists;


    /**
     * @param lists is lists of elements
     */
    public ElementCreator(List<List<String>> lists) {
        this.lists = lists;
    }

    /**
     * @param find is string to find
     * @param index is index (for atomic number = 0, for name = 2 etc)
     * @return element or nothing
     */

    public Optional<Element> singleElem(String find, Integer index){
        for (List<String> l: this.lists) {
            if(l.contains(find) && l.indexOf(find) == index){
                Element element = new Element(Integer.parseInt(l.get(0)), l.get(1), l.get(2),
                        l.get(3), l.get(4), Integer.parseInt(l.get(5)));
                return Optional.of(element);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Element> findByAtomicNumber(int num) {
        return singleElem(String.valueOf(num), 0);
    }

    @Override
    public Optional<Element> findBySymbol(String symbol) {
        return singleElem(symbol, 1);
    }

    @Override
    public Optional<Element> findByName(String name) {
        return singleElem(name, 2);
    }

    @Override
    public List<Element> findByYear(int year) {
        List<Element> elements = new ArrayList<>();
        for (List<String> l: this.lists) {
            if(l.contains(String.valueOf(year)) && l.indexOf(String.valueOf(year)) == 5){
                Element element = new Element(Integer.parseInt(l.get(0)), l.get(1), l.get(2),
                        l.get(3), l.get(4), Integer.parseInt(l.get(5)));
                elements.add(element);
            }
        }
        return elements;
    }
}

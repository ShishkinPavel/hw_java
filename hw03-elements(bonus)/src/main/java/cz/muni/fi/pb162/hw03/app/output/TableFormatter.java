package cz.muni.fi.pb162.hw03.app.output;

import cz.muni.fi.pb162.hw03.app.impl.Element;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Formatting of {@link Element} instances as ASCI table
 */
public class TableFormatter implements ElementFormatter {

    private static final String ROW_SEPARATOR = "-------------------------------------------------" +
            "------------------------------------------------------";
    private static final String HEAD_SEPARATOR = "=================================================" +
            "======================================================";


    @Override
    public String format(Element element) {
        return lines(head(), row(element), ROW_SEPARATOR);
    }

    @Override
    public String format(Collection<Element> elements) {
        List<String> rows = elements.stream().map(this::row).collect(toList());
        return lines(head(), lines(rows), ROW_SEPARATOR);
    }

    private String head() {
        String row = row("#", "Symbol", "Name", "Standard State", "Group Block", "Year");
        return lines(ROW_SEPARATOR, row, HEAD_SEPARATOR);
    }

    private String row(Element element) {
        return row(
                String.valueOf(element.getAtomicNumber()),
                element.getSymbol(),
                element.getName(),
                element.getStandardState(),
                element.getGroupBlock(),
                String.valueOf(element.getYearDiscovered())
        );
    }

    private String row(String... data) {
        if (data.length != 6) {
            throw new IllegalArgumentException("Incorrect number of values");
        }
        return "|" +
                String.format("%10s", data[0]) + "|" +
                String.format("%10s", data[1]) + "|" +
                String.format("%20s", data[2]) + "|" +
                String.format("%20s", data[3]) + "|" +
                String.format("%25s", data[4]) + "|" +
                String.format("%10s", data[5]) +
                "|";
    }

    private String lines(String... lines) {
        return lines(Arrays.asList(lines));
    }

    private String lines(Collection<String> lines) {
        return lines.stream()
                .collect(joining(System.lineSeparator()));
    }
}

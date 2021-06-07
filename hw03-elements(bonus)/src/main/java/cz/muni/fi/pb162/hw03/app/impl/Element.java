package cz.muni.fi.pb162.hw03.app.impl;

import java.util.Objects;

/**
 * Representation of element
 */
public final class Element {
    private final int atomicNumber;
    private final String symbol;
    private final String name;
    private final String standardState;
    private final String groupBlock;
    private final int yearDiscovered;

    /**
     * Constructs element instance
     *
     * @param atomicNumber Atomic number
     * @param symbol Symbol
     * @param name Name
     * @param standardState Standard state of occurrence
     * @param groupBlock Group block in periodic table
     * @param yearDiscovered Year of discovery
     */
    public Element(
            int atomicNumber, String symbol, String name,
            String standardState, String groupBlock, int yearDiscovered
    ) {
        this.atomicNumber = atomicNumber;
        this.symbol = symbol;
        this.name = name;
        this.standardState = standardState;
        this.groupBlock = groupBlock;
        this.yearDiscovered = yearDiscovered;
    }

    public int getAtomicNumber() {
        return this.atomicNumber;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public String getStandardState() {
        return this.standardState;
    }

    public String getGroupBlock() {
        return this.groupBlock;
    }

    public int getYearDiscovered() {
        return this.yearDiscovered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Element element = (Element) o;
        return atomicNumber == element.atomicNumber && yearDiscovered == element.yearDiscovered &&
                Objects.equals(symbol, element.symbol) && Objects.equals(name, element.name) &&
                Objects.equals(standardState, element.standardState) &&
                Objects.equals(groupBlock, element.groupBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atomicNumber, symbol, name, standardState, groupBlock, yearDiscovered);
    }
}
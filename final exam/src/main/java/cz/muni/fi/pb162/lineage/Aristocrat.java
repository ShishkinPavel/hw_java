package cz.muni.fi.pb162.lineage;

import java.util.List;
import java.util.Objects;

public class Aristocrat implements Comparable<Aristocrat> {
    private final int birth;
    private final String name;
    private final int death;
    private final List<String> sons;


    public Aristocrat(int birth, String name, int death, List<String> sons)  {
        if (birth > death){
            throw new IllegalArgumentException("death cant be earlier than birth");
        }
        if (name == null || name.length() == 0){
            throw new IllegalArgumentException("aristocrat should be named");
        }
        this.birth = birth;
        this.name = name;
        this.death = death;
        this.sons = sons;
    }

    public List<String> getSons() {
        return sons;
    }

    @Override
    public String toString() {
        return birth + "-" + death + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aristocrat that = (Aristocrat) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }


    @Override
    public int compareTo(Aristocrat o) {
        if (o.birth == this.birth){
            return this.name.compareTo(o.name);
        }
        return o.birth - this.birth;
    }
}

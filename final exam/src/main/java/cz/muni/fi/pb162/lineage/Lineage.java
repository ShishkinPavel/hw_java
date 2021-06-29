package cz.muni.fi.pb162.lineage;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Lineage {
    private final String lineage;
    private final TreeMap<String, TreeMap<String, Aristocrat>> allPeople = new TreeMap<>();


    public Lineage(String lineage) {
        this.lineage = lineage;
        allPeople.putIfAbsent(lineage, new TreeMap<>());
    }

    void read(InputStream is) throws LineageException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        readBuffered(br);
    }

    void read(File file) throws LineageException {
        try (BufferedReader b = new BufferedReader(new FileReader(file))){
            readBuffered(b);
        } catch (IOException e) {
            throw new LineageException("IO exception found");
        }
    }

    private void readBuffered(BufferedReader br) throws LineageException {
        try {
            String line = br.readLine();
            while ( line != null) {
                String[] actualLine = line.split(":", 2);
                String[] arist = actualLine[0].split(",", 3);
                String name = arist[0];
                int birth = Integer.parseInt(arist[1]);
                int death = Integer.parseInt(arist[2]);

                List<String> sons = new ArrayList<>();
                if (actualLine.length > 1) {
                    sons = Arrays.asList(actualLine[1].split(","));
                }
                allPeople.get(lineage).putIfAbsent(name, new Aristocrat(birth, name, death, sons));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new LineageException("IO exception found");
        }
    }

    Aristocrat getAristocrat(String name){
        return allPeople.get(lineage).get(name);
    }

    Aristocrat getFirstAncestor(){
        return allPeople.get(lineage).get(allPeople.get(lineage).firstKey());
    }

    void writeFamilyTree(OutputStream os) throws LineageException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        try {
            String space = "";
            writer.write(lineage + ":");
            writer.newLine();
            Aristocrat aristocrat = getFirstAncestor();
            writeHelper(os, aristocrat, space, writer);
            writer.flush();
        } catch (IOException e) {
            throw new LineageException("Wrong output file");
        }
    }

    void writeHelper(OutputStream os, Aristocrat aristocrat, String space, BufferedWriter writer) throws IOException {
        writer.write(space + aristocrat.toString());
        writer.newLine();
        for (String strings: aristocrat.getSons()) {
            Aristocrat newAristocrat = allPeople.get(lineage).get(strings);
            writeHelper(os, newAristocrat, space + " ", writer);
        }
    }
}

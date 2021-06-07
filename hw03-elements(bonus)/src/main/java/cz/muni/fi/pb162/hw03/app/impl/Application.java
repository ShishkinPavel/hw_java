package cz.muni.fi.pb162.hw03.app.impl;

import cz.muni.fi.pb162.hw03.app.output.Messages;
import cz.muni.fi.pb162.hw03.app.output.TableFormatter;
import cz.muni.fi.pb162.hw03.csv.CsvParser;
import cz.muni.fi.pb162.hw03.csv.impl.CsvToolkit;
import com.beust.jcommander.Parameter;
import cz.muni.fi.pb162.hw03.app.cmd.CommandLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Application CLI
 */
@SuppressWarnings("FieldMayBeFinal")
public final class Application {

    @Parameter(names = "--help", help = true)
    private boolean showUsage = false;

    @Parameter(names = {"--symbol", "-s"})
    private String symbol;

    @Parameter(names = "--name")
    private String name;

    @Parameter(names = {"--number", "-n"})
    private Integer number;

    @Parameter(names = {"--year", "-y"})
    private Integer year;



    /**
     * Application entry point
     *
     * @param args command line arguments of the application
     * @throws IOException on unrecoverable IO error
     */
    public static void main(String[] args) throws IOException {
        Application app = new Application();

        CommandLine cli = new CommandLine(app);
        cli.parseArguments(args);

        if (app.showUsage) {
            cli.showUsage();
        } else {
            app.run(cli);
        }
    }

    /**
     * Application runtime logic
     *
     * @param cli command line interfacee
     */
    private void run(CommandLine cli) throws IOException {

        // check if count of filters >2
        boolean[] boolOfFilters = new boolean[]{this.symbol != null, this.name != null,
                this.year != null, this.number != null};
        List<Integer> countOfFilters = IntStream.range(0, boolOfFilters.length)
                        .filter(i -> boolOfFilters[i])
                        .boxed()
                        .collect(Collectors.toList());
        if (countOfFilters.size() > 1){
            System.out.println(Messages.SINGLE_FILTER_REQUIRED);
        }


        CsvParser parser = CsvToolkit.parser(CsvParser.DEFAULT_DELIMITER, CsvParser.DEFAULT_CHARSET);

        List<List<String>> result = new ArrayList<>();
        parser.open(getClass().getClassLoader().getResourceAsStream("elements.csv")).forEach(result::add);

        Optional<Element> optionalElement = Optional.empty();

        if(this.symbol!=null){
            optionalElement = new ElementCreator(result).findBySymbol(this.symbol);
        }

        if(this.name!=null){
            optionalElement = new ElementCreator(result).findByName(this.name);
        }

        if(this.number!=null){
            optionalElement = new ElementCreator(result).findByAtomicNumber(this.number);
        }

        if(this.year!=null){
            List<Element> elementList = new ElementCreator(result).findByYear(this.year);
            System.out.println(new TableFormatter().format(elementList));
        } else {
            if (optionalElement.isPresent()) {
                System.out.println(new TableFormatter().format(optionalElement.get()));
            }else {
                System.out.println(Messages.NO_ELEMENT_FOUND);
            }
        }
        }



}

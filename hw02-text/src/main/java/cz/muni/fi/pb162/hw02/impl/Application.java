package cz.muni.fi.pb162.hw02.impl;

import com.beust.jcommander.Parameter;
import cz.muni.fi.pb162.hw02.FileLoader;
import cz.muni.fi.pb162.hw02.Messages;
import cz.muni.fi.pb162.hw02.cmd.CommandLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Application class represents the command line interface of this application.
 * <p>
 * You are expected to implement  the  {@link Application#run(CommandLine)} method
 *
 * @author jcechace
 */
public class Application {



    @Parameter(names = "--help", help = true)
    private boolean showUsage = false;

    @Parameter(names = "--file", required = true)
    private String path;

    @Parameter(names = {"unique", "-u"})
    private boolean unique = false;

    @Parameter(names = "count")
    private boolean count = false;

    @Parameter(names = {"sort", "-s"})
    private boolean sort = false;

    @Parameter(names = {"duplicates", "-d"})
    private boolean duplicates = false;

    @Parameter(names = "lines")
    private boolean lines = false;

    @Parameter(names = "sizes")
    private boolean sizes = false;

    @Parameter(names = "similar")
    private boolean similar = false;


    /**
     * Application entry point
     *
     * @param args command line arguments of the application
     */
    public static void main(String[] args) {
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
     * @param cli command line interface
     */
    private void run(CommandLine cli) {
        List<String> file = new ArrayList<>();
        FileLoader fileLoader = new FileLoader();

        if(this.unique && this.duplicates){
            System.err.println(Messages.INVALID_OPTION_COMBINATION);
        }

        try{
            file = fileLoader.loadAsLines(path);
        } catch (IOException e){
            System.err.printf(Messages.IO_ERROR, path);
        }

        if(this.sort){
            Collections.sort(file);
        }

        if(this.duplicates){
            Duplicates duplicates = new Duplicates(file);
            file = duplicates.getResult();
        }

        if(this.unique){
            Unique unique = new Unique(file);
            file = unique.getResult();
        }

        if(this.count){
            System.out.println(file.size());
            return;
        }
        if(this.sizes){
            Sizes sizes = new Sizes(file);
            file = sizes.getResult();
        }
        if (this.similar){
            Similar similar = new Similar((ArrayList<String>) file);
            System.out.println(similar.getResult());
            return;
        }
        file.forEach(System.out::println);
    }
}

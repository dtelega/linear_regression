package algo;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Random;

public class Util {

    public static Options parseArguments(final String[] args) {
        // program type
        Option programType = new Option("p", "program", true, "program type");
        programType.setRequired(true);

        // dataset file path
        Option datasetFile = new Option("d", "dataset", true, "dataset file path");
        datasetFile.setRequired(false);

        Options options = new Options();
        options.addOption(programType);
        options.addOption(datasetFile);

        return options;
    }

    public static void validateArguments(final String programType, final String datasetFile) {
       if (programType.equals("t") && datasetFile == null) {
           System.out.println("Error, no datasetFile path");
           System.exit(1);
       }
    }
}

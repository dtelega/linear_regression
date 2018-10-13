package algo;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Random;

import java.io.*;

public class Util {

    public static int findLengthFile(String datasetPath) {
        int m = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(datasetPath));
            while (reader.readLine() != null) m++;
              reader.close();
        } catch (Exception ex) {
            System.out.println("No such file: "+datasetPath);
            System.exit(1);
        }
       
        return m;
    }

    public static Options parseArguments(final String[] args) {
        // program type
        Option programType = new Option("p", "program", true, "program type");
        programType.setRequired(false);

        // dataset file path
        Option datasetFile = new Option("d", "dataset", true, "dataset file path");
        datasetFile.setRequired(false);

        Options options = new Options();
        options.addOption(programType);
        options.addOption(datasetFile);

        return options;
    }

    public static void usageMes() {
        System.out.println("Usage:");
        System.out.println("\t-p [p/t] [predict/trainer]");
        System.out.println("\t-d ..pathToDataset/datasetFile (if trainer)");
    }

    public static void validateArguments(final String programType, final String datasetFile) {
      if (programType == null && datasetFile == null) {
        // show info
        usageMes(); 
        
        System.exit(0);

      }
       if (programType.equals("t") && datasetFile == null) {
           System.out.println("Error, no datasetFile path");
           System.exit(1);
       }
      
    }
}

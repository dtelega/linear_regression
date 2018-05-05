import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

import static algo.Util.*;

public class Main {
    private static float theta0 = 0;
    private static float theta1 = 0;

    private static float estimatePrice(int mileage) {
        return theta0 + (theta1 * mileage);
    }

    public static void main(String[] args) {
        
        File thetasData = new File("thetasData.txt");
        
        if (thetasData.exists()) {
            try {
                FileReader fr = new FileReader(thetasData);
                char [] a = new char[200];
                fr.read(a);
                String b = new String(a);
                fr.close();
                String[] subStr;
                subStr = b.split(" ");
                theta0 = Float.parseFloat(subStr[0]);
                theta1 = Float.parseFloat(subStr[1]);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        else {
            try {
                FileWriter writer = new FileWriter(thetasData);
                writer.write("0.0 0.0");
                writer.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Options options = parseArguments(args);
        String datasetPath;
        String programType;

        try {
            CommandLine cmd = new BasicParser().parse(options, args);
            programType = cmd.getOptionValue("program");
            datasetPath = cmd.getOptionValue("dataset");
            
            validateArguments(programType, datasetPath);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return;
        }

        if (programType.equals("p")) {
            System.out.println("Enter the mileage please:");
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            try {
                System.out.println(estimatePrice(Integer.parseInt(s)));
            } catch (NumberFormatException e) {
                System.out.println("Bad value");
                System.exit(1);
            }
        }
        else {
            // do shit
        }
    }
}

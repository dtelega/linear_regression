import org.apache.commons.cli.*;
import java.io.*;
import java.util.Scanner;


import static algo.Util.*;
import static algo.Plot.*;
import static algo.Trainer.*;

public class Main {
    private static double theta0 = 0;
    private static double theta1 = 0;

    private static double estimatePrice(double mileage, double tmpTheta0, double tmpTheta1) {
        double price = tmpTheta0 + (tmpTheta1 * mileage);
        if (price < 0 ) {
            price = 0 ;
        }
        return price;
    }

    public static void main(String[] args) {
        
        File thetasData = new File("thetasData.log");
        
        if (thetasData.exists()) {
            try {
                FileReader fr = new FileReader(thetasData);
                char [] a = new char[200];
                fr.read(a);
                String b = new String(a);
                fr.close();
                String[] subStr;
                subStr = b.split(" ");
                theta0 = Double.parseDouble(subStr[0].trim());
                theta1 = Double.parseDouble(subStr[1].trim());

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error");
                System.exit(0);
            }

        }
        else {
            try {
                FileWriter writer = new FileWriter(thetasData);
                writer.write("0.0 0.0");
                // writer.close();

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
            System.out.print("Enter the mileage please: ");
            Scanner scan = new Scanner(System.in);
            String s = scan.nextLine();
            try {
                if (Double.parseDouble(s) < 0 || Double.parseDouble(s) > 2147483647) {
                    System.out.println("Bad value");
                    System.exit(1);
                }
                System.out.print("Estimate price: ");
                if (estimatePrice(Double.parseDouble(s), theta0, theta1) == 0) {
                    System.out.println("trash :D (almost 0)");
                } else {
                    System.out.println(estimatePrice(Double.parseDouble(s), theta0, theta1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Bad value");
                System.exit(1);
            }
        }
        else if (programType.equals("t")){
            try {
                startTraining(datasetPath);
            } catch (Exception ex) {
                System.out.println("smth wrong with data file");
                ex.printStackTrace();
            }
        
        } else {
            usageMes();
        }
    }
}

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
import java.util.Scanner;
import java.io.BufferedReader;

import static algo.Util.*;

public class Main {
    private static int theta0 = 0;
    private static int theta1 = 0;

    private static int estimatePrice(int mileage, int tmpTheta0, int tmpTheta1) {
        return tmpTheta0 + (tmpTheta1 * mileage);
    }

    private static int findLengthFile(String datasetPath) {
        int m = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(datasetPath));
            while (reader.readLine() != null) m++;
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return m;
    }
    private static void startTraining(String datasetPath) {
        int tmp0;
        int tmp1;
        int i = 0;

        int m = findLengthFile(datasetPath);
        int x [] = new int[m];
        int y [] = new int[m];
        try {
            Scanner sc = new Scanner(new File(datasetPath));
            
            String [] splitted;
            splitted = sc.nextLine().split(",");
            while(sc.hasNext()){
                splitted = sc.nextLine().split(",");
                x[i] = Integer.parseInt(splitted[0]);
                y[i] = Integer.parseInt(splitted[1]);
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
/*
        // check x y
        for (int j = 0; j < i; j++)
            System.out.println(x[j]+","+y[j]);
        // end
*/
        for (int q = 200; q>0; q--) {
            tmp0 = 0;
            tmp1 = 0;
            for (int j = 0; j < m; j++) {
                tmp0 += (estimatePrice(x[j], theta0, theta1) - y[j]);
                tmp1 += (estimatePrice(x[j], theta0, theta1) - y[j]) * x[j];
                //System.out.println("tmp = "+tmp0+"   "+tmp1);
            }
            theta0 -= (tmp0 * 3 / 10) / m;
            theta1 -= (tmp1 * 3 / 10) / m;
            System.out.println(theta0+"    "+theta1);
        }
        System.out.println(theta0+"    "+theta1);
    }


    public static void main(String[] args) {
        
        File thetasData = new File(".thetasData.txt");
        
        if (thetasData.exists()) {
            try {
                FileReader fr = new FileReader(thetasData);
                char [] a = new char[200];
                fr.read(a);
                String b = new String(a);
                fr.close();
                String[] subStr;
                subStr = b.split(" ");
                theta0 = Integer.parseInt(subStr[0]);
                theta1 = Integer.parseInt(subStr[1]);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error");
                System.exit(0);
            }

        }
        else {
            try {
                FileWriter writer = new FileWriter(thetasData);
                writer.write("0 0");
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
                System.out.println(estimatePrice(Integer.parseInt(s), theta0, theta1));
            } catch (NumberFormatException e) {
                System.out.println("Bad value");
                System.exit(1);
            }
        }
        else {
            startTraining(datasetPath);
            try {
                FileWriter writer = new FileWriter(thetasData);
                writer.write(theta0 + " " + theta1);
                writer.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}

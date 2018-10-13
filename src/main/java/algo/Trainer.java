package algo;

import java.io.*;
import java.util.Scanner;
import java.math.BigDecimal;

import static algo.Util.*;
import static algo.Plot.*;

public class Trainer {
	private static double theta0 = 0;
    private static double theta1 = 0;

	public static void startTraining(String datasetPath) {
    
        int tmp0;
        int tmp1;
  
        int i = 0;
        int m = 0;
        try {
	        m = findLengthFile(datasetPath);
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
		if (m <= 2) {
			System.out.println("Shiit, length of file <= 2\n TODO: 2 dots yrovnenie");
			System.exit(1);
		}

        int x [] = new int[m];
        int y [] = new int[m];
        try {
            Scanner sc = new Scanner(new File(datasetPath));
            
            String [] splitted;
            splitted = sc.nextLine().split(",");
            while(sc.hasNext()){
                splitted = sc.nextLine().split(",");
                if (splitted.length != 2){
                	System.out.println("Error");
                	System.exit(1);
                }
                x[i] = Integer.parseInt(splitted[0]);
                y[i] = Integer.parseInt(splitted[1]);
                if (x[i] < 0 || y[i] < 0) {
                	System.out.println("Error: price/km < 0");
                	System.exit(1);
                }
                i++;
            }
        } catch (Exception ex) {
        	System.out.println("Bad value");
            System.exit(1);
        }
   

        double xSum = 0;
        double ySum = 0;
        double n = i;
        // double xSquared = 0;
        BigDecimal xSquared = new BigDecimal("0");
        // double xY = 0;
        BigDecimal xY = new BigDecimal("0");

//        BigDecimal d = new BigDecimal(1.5);
        for (int j = 0; j < i; j++) {
            xSum += x[j];
            ySum += y[j];
            xSquared = xSquared.add(BigDecimal.valueOf(x[j]).multiply(BigDecimal.valueOf(x[j])));
            xY = xY.add(BigDecimal.valueOf(x[j] * y[j]));
        }
        double numerator = n * xY.doubleValue() - xSum * ySum;
        
        double slope = numerator / (xSquared.multiply(BigDecimal.valueOf(n)).add(BigDecimal.valueOf(- xSum * xSum)).doubleValue());

        double intercept = ((double)(1 / n)) *ySum - (double)(slope*xSum) * ((double)1/n);
        
        System.out.println("Build for: y="+slope+"*x + "+intercept);
        
        theta0 = intercept;
        theta1 = slope;
        if (Double.isNaN(theta0) || Double.isNaN(theta1)){
            System.out.println("bad data");
            System.exit(1);
        }
        try {
            FileWriter writer = new FileWriter("thetasData.log");
            writer.write(theta0 + " " + theta1);
            writer.close();

        } catch (IOException ex) {
        	System.out.println("Sry, smth goes wrong");
            ex.printStackTrace();
            System.exit(1);
        }
        paint(x, y,i, theta0, theta1);

        
    }




}
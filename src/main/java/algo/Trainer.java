package algo;

import java.io.*;
import java.util.Scanner;

import static algo.Util.*;
import static algo.Plot.*;

public class Trainer {
	private static double theta0 = 0;
    private static double theta1 = 0;

	public static void startTraining(String datasetPath) {
    
        int tmp0;
        int tmp1;
  
        int i = 0;

/*
need to div long int to double, because overflow
*/
        int m = findLengthFile(datasetPath);

        if (m <= 2) {
        	System.out.println("Shiit, find me");
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
                x[i] = Math.round(Integer.parseInt(splitted[0]) / 1000);
                y[i] = Integer.parseInt(splitted[1]);
                i++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
   

        double xSum = 0;
        double ySum = 0;
        double n = i;
        double xSquared = 0;
        double xY = 0;
        for (int j = 0; j < i; j++) {
            xSum += x[j];
            ySum += y[j];
            xSquared += x[j] * x[j];
            xY += x[j] * y[j];
        }
        // System.out.println(xSum);
        // System.out.println(ySum);
        // System.out.println(xSquared);
        // System.out.println(xY);
        // System.out.println("n= "+n);

        double numerator = (n * xY - xSum * ySum);
        // System.out.println(numerator);
        double slope = (n * xY - xSum * ySum) / (n * xSquared - xSum * xSum);
        
        double intercept = ((double)(1 / n)) *ySum - (double)(slope*xSum) * ((double)1/n);
        System.out.println("y="+slope+"*x + "+intercept);
        // System.out.println(intercept);

        theta0 = intercept;
        theta1 = slope;
        if (Double.isNaN(theta0) || Double.isNaN(theta1)){
            System.out.println("bad data");
            System.exit(1);
        }

        paint(x, y,i, theta0, theta1);
    }

}
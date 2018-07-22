package algo;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Plot extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;

  public Plot(String title, int x[], int y[], int len, double intersept, double slope) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset(x,y,len,intersept,slope);

    
    // Create chart
    JFreeChart chart = ChartFactory.createScatterPlot(
        "Boys VS Girls weight comparison chart", 
        "X-Axis", "Y-Axis", dataset);

    
    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  private XYDataset createDataset(int x[], int y[], int len, double intersept, double slope) {
    XYSeriesCollection dataset = new XYSeriesCollection();

    //Boys (Age,weight) series
    XYSeries series1 = new XYSeries("Boys");
    
    for (int i = 0; i < len; i++) {
      series1.add(x[i], y[i]);
    }
    dataset.addSeries(series1);
    /*
   //Girls (Age,weight) series
    XYSeries series2 = new XYSeries("Girls");
    series2.add(1, 72.5);
    dataset.addSeries(series2);
  */
    return dataset;
  }

  public static void paint(int x[], int y[], int len, double intersept, double slope) {

    SwingUtilities.invokeLater(() -> {
      System.out.println("Hello");
      Plot example = new Plot("Title", x, y, len, intersept, slope);
      example.setSize(800, 400);
      example.setLocationRelativeTo(null);
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      example.setVisible(true);
    });
  }
}
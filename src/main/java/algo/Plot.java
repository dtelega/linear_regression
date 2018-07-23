package algo;


import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.AbstractRenderer;

public class Plot extends JFrame {
  private static final long serialVersionUID = 6294689542092367723L;

  public Plot(String title, int x[], int y[], int len, double intersept, double slope) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset(x,y,len,intersept,slope);

    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    // Create chart
    JFreeChart chart = ChartFactory.createXYLineChart(
        "Chart",
        "km",
        "price",
        dataset,
        PlotOrientation.VERTICAL,
        true, true, false);
    renderer.setSeriesLinesVisible(0, false);
    renderer.setSeriesShapesVisible(0, true);
    renderer.setSeriesLinesVisible(1, true);
    renderer.setSeriesShapesVisible(1, false);
    
    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    plot.setRenderer(renderer);
    setContentPane(panel);
  }

  private XYDataset createDataset(int x[], int y[], int len, double intersept, double slope) {
    XYSeriesCollection dataset = new XYSeriesCollection();

    //data series
    XYSeries series1 = new XYSeries("data");
    
    for (int i = 0; i < len; i++) {
      series1.add(y[i], x[i]);
    }
    dataset.addSeries(series1);

    // linear regression series
    XYSeries series2 = new XYSeries("graph");

    series2.add(-10, (intersept + slope * (-10)));
    series2.add(30, (intersept + slope * (30)));
    dataset.addSeries(series2);

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
      example.setSize(1600, 800);
      example.setLocationRelativeTo(null);
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      example.setVisible(true);
    });
  }
}
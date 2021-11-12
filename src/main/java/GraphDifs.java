import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.util.ArrayList;

public class GraphDifs extends JFrame {
    private static final long serialVersionUID = 1L;

    public GraphDifs(String applicationTitle, ArrayList<Double> arraySizes,ArrayList<Double> percentage) {
        super(applicationTitle);
        //Creates a sample dataset
        XYSeries s = new XYSeries("Merge sort single vs multi");
        for(int i =0;i<arraySizes.size();i++){
            s.add(arraySizes.get(i),percentage.get(i));
        }
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Merge sort single vs multi", s.toArray());
        // based on the dataset we create the chart
        JFreeChart chart =ChartFactory.createXYLineChart("Differnce between multi threaded and single threaded merge sort "+arraySizes.size()+" data points","array size of merge sort","Merge sort beat",dataset);

        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(chart);

        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        // add to contentPane
        setContentPane(chartPanel);
    }
    public static void main(String[] args) {
        //GraphDifs chart = new GraphDifs("Browser Usage Statistics", "Which Browser are you using?");
        //chart.pack();
        //chart.setVisible(true);
    }
}

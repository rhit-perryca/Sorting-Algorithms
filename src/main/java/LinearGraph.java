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
import org.jfree.chart.ChartUtils;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class LinearGraph extends JFrame {
    private static final long serialVersionUID = 1L;
    final int windowW = 500;
    final int windowH = 270;

    public LinearGraph(String applicationTitle, String XaxisName, String yAxisName, String graphName, String dataSetName, ArrayList<Double> x, ArrayList<Double> y, boolean saveToPng) {
        super(applicationTitle);
        //Creates a sample dataset
        XYSeries s = new XYSeries(dataSetName);
        for (int i = 0; i < x.size(); i++) {
            s.add(x.get(i), y.get(i));
        }
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Merge sort single vs multi", s.toArray());
        // based on the dataset we create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(graphName, XaxisName, yAxisName, dataset);
        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(windowW, windowH));

        // add to contentPane
        setContentPane(chartPanel);
        if (saveToPng) {
            OutputStream out = null;
            try {
                out = new FileOutputStream(graphName.replace(' ', '_') + ".png");
                ChartUtils.writeChartAsPNG(out,
                        chart,
                        windowW,
                        windowH);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public LinearGraph(String applicationTitle, String XaxisName, String yAxisName, String graphName, ArrayList<String> dataSetNames, ArrayList<ArrayList<Double>> x, ArrayList<ArrayList<Double> >y, boolean saveToPng) {
        super(applicationTitle);
        //Creates a sample dataset
        ArrayList<XYSeries> lines = new ArrayList<>();
        for (int i = 0; i < x.size(); i++) {
            XYSeries s = new XYSeries(dataSetNames.get(i));
            for (int j = 0; j < x.get(i).size(); j++) {
                s.add(x.get(i).get(j), y.get(i).get(j));
            }
            lines.add(s);
        }
        DefaultXYDataset dataset = new DefaultXYDataset();
        for(int i=0;i<dataSetNames.size();i++){
            dataset.addSeries(dataSetNames.get(i), lines.get(i).toArray());
        }
        // based on the dataset we create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(graphName, XaxisName, yAxisName, dataset);
        // Adding chart into a chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // settind default size
        chartPanel.setPreferredSize(new java.awt.Dimension(windowW, windowH));

        // add to contentPane
        setContentPane(chartPanel);
        if (saveToPng) {
            OutputStream out = null;
            try {
                out = new FileOutputStream(graphName.replace(' ', '_') + ".png");
                ChartUtils.writeChartAsPNG(out,
                        chart,
                        windowW,
                        windowH);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

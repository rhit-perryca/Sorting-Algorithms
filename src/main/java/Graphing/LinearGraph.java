package Graphing;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.chart.ChartUtils;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class LinearGraph extends JFrame {
    private static final long serialVersionUID = 1L;
    final int windowW = 500;
    final int windowH = 270;

    public LinearGraph(String applicationTitle,String graphName, String XaxisName, String yAxisName,  Dataset data, boolean saveToPng) {
        super(applicationTitle);
        //Creates a sample dataset
        DefaultXYDataset dataset = new DefaultXYDataset();
        if(data.isFindFitLine())
            dataset.addSeries(data.getRegLine().name,data.getRegLine().getData().toArray());
        dataset.addSeries(data.getName(), data.getData().toArray());
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
                String name="";
                for(char c:graphName.toCharArray()){
                    if(c==' '){
                        name+='_';
                    }else if(c==':'){
                        name+='-';
                    }else if(c!='\n'){
                        name+=c;
                    }
                }
                out = new FileOutputStream(name + ".png");
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public LinearGraph(String applicationTitle, String graphName,String XaxisName, String yAxisName,  ArrayList<Dataset> data, boolean saveToPng) {
        super(applicationTitle);
        //Creates a sample dataset
        DefaultXYDataset dataset = new DefaultXYDataset();
        for(Dataset d:data){
            dataset.addSeries(d.getName(), d.getData().toArray());
            if(d.isFindFitLine())
                dataset.addSeries(d.getRegLine().name,d.getRegLine().getData().toArray());
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

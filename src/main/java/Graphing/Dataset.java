package Graphing;

import org.jfree.data.xy.XYSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Dataset {
    XYSeries data;
    Dataset regLine;

    public Dataset getRegLine() {
        return regLine;
    }

    String name;
    boolean findFitLine;

    public XYSeries getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    public boolean isFindFitLine() {
        return findFitLine;
    }

    public Dataset(ArrayList<Double> x, ArrayList<Double>y, String name, boolean findFitLine) {
        this.findFitLine=findFitLine;
        data=new XYSeries(name);
        for(int i =0;i<x.size();i++){
            data.add(x.get(i),y.get(i));
        }
        this.name = name;
        if(findFitLine){
            double ysum = 0;
            double xsum = 0;
            double xsquaredSum = 0;
            double xysum = 0;
            for (int i = 0; i < x.size(); i++) {
                Double yNum = y.get(i);
                Double xNum = x.get(i);
                ysum += yNum;
                xsum += xNum;
                xsquaredSum += (xNum * xNum);
                xysum += yNum * xNum;
            }

            double xsumsquared = xsum * xsum;
            double m = ((x.size() * xysum) - (xsum * ysum)) / ((x.size() * xsquaredSum) - xsumsquared);
            double b = (ysum - m * xsum) / (x.size());
            ArrayList<Double> xReg = new ArrayList<>();
            ArrayList<Double> yReg = new ArrayList<>();
            xReg.add(0.0);
            yReg.add(0.0);
            double endPoint = x.get(x.size() - 1) * m + b;
            xReg.add(x.get(x.size() - 1));
            yReg.add(endPoint);
            regLine = new Dataset(xReg, yReg, name + "(fit line)", false);
        }
    }
}

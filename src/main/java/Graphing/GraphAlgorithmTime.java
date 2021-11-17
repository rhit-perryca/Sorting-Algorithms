package Graphing;

import Main.MainApp;
import Sort.SortingAlgorithm;

import java.util.ArrayList;

public class GraphAlgorithmTime {
    public static Dataset makeGraph(int runCount, int increment, int avgRuns, boolean worstCase, boolean saveImg, SortingAlgorithm algorithm, String frameName, String graphTitle, String xName, String yName){
        ArrayList<Double>x=new ArrayList<>(),y=new ArrayList<>();
        for (int i = increment; i <= runCount; i+=increment) {
            measureTime(i, avgRuns, y, x, algorithm,worstCase);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d = new Dataset(x,y,algorithm.getName(),true);
        LinearGraph graph = new LinearGraph(frameName,graphTitle,xName,yName,d,saveImg);
        graph.pack();
        graph.setVisible(true);
        return d;
    }
    public static Dataset makeGraph(int runCount, int increment, int avgRuns, boolean worstCase, SortingAlgorithm algorithm){
        ArrayList<Double>x=new ArrayList<>(),y=new ArrayList<>();
        for (int i = increment; i <= runCount; i+=increment) {
            measureTime(i, avgRuns, y, x, algorithm,worstCase);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d = new Dataset(x,y,algorithm.getName(),true);
        return d;
    }

    public static void measureTime(int size, int averagingCount, ArrayList<Double> times, ArrayList<Double> sortSize, SortingAlgorithm algorithm, boolean worstCase){
        double timesSum = 0;
        for (int i = 0; i < averagingCount; i++) {
            ArrayList<Integer> list = MainApp.getArray(size,worstCase);

            //start time on multi
            long multiStart = System.nanoTime() / 1000000;

            //start sorting using multithreaded
            algorithm.sort(list);

            //end multi time
            long multiEnd = System.nanoTime() / 1000000;

            //get time spent sorting
            long totalTimeSelection= multiEnd - multiStart;

            //print time spent on multi
            /*System.out.println("Total multi: "+totalTimeMulti);

            //print time spend on single
            System.out.println("Total single: "+totalTimeSingle);

            //print time diff
            System.out.println("Total difference(single-multi): "+(totalTimeSingle-totalTimeMulti));*/

            timesSum += totalTimeSelection;
        }
        sortSize.add((double)size);
        times.add(timesSum/size);
    }
}

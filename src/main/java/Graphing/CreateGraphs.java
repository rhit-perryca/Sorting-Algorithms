package Graphing;

import Sort.InsertionSort;
import Sort.Merge.MergeSingle;
import Sort.Merge.MergeSort;
import Main.MainApp;
import Sort.Merge.MergeMulti;
import Sort.SelectionSort;

import java.util.ArrayList;

public class CreateGraphs {
    public static void graphAll(int arraySizeCount,int increment,int avgRuns,boolean worstCase, boolean saveToPng,boolean showGraph){
        ArrayList<Dataset> data = new ArrayList<>();


        data.add(GraphAlgorithmTime.makeGraph(arraySizeCount,increment,avgRuns,worstCase,new SelectionSort()));

        data.add(GraphAlgorithmTime.makeGraph(arraySizeCount,increment,avgRuns,worstCase,new InsertionSort()));

        data.add(GraphAlgorithmTime.makeGraph(arraySizeCount,increment,avgRuns,worstCase,new MergeSingle()));

        data.add(GraphAlgorithmTime.makeGraph(arraySizeCount,increment,avgRuns,worstCase,new MergeMulti()));

        LinearGraph graph = new LinearGraph("Graph","Sorting algorithms compared","Array Size","Sort delay(ms)",data,saveToPng);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMerges(int arraySizeCount,int increment,int avgRuns,boolean worstCase,boolean saveToPng){
        ArrayList<Dataset> data = new ArrayList<>();

        data.add(GraphAlgorithmTime.makeGraph(arraySizeCount,increment,avgRuns,worstCase,new MergeSingle()));

        data.add(GraphAlgorithmTime.makeGraph(arraySizeCount,increment,avgRuns,worstCase,new MergeMulti()));

        LinearGraph graph = new LinearGraph("Graph","Merge sort algorithm single threaded vs multi compared","Array Size","Sort delay(ms)",data,saveToPng);
        graph.pack();
        graph.setVisible(true);
    }
    public static void graphMergeSortTime(int runCount, int increment, int avgRuns, boolean worstCase, boolean multiThreaded, boolean saveImg){
        MergeSort sort;
        if(multiThreaded){
            sort=new MergeMulti();
        }else{
            sort=new MergeSingle();
        }
        GraphAlgorithmTime.makeGraph(runCount,increment,avgRuns,worstCase,saveImg,sort,"Graph","Merge Sort Time","Array Size","Time(ms)");
    }
    public static void graphSelectionSortTime(int runCount, int increment, int avgRuns, boolean worstCase, boolean saveImg){
        GraphAlgorithmTime.makeGraph(runCount,increment,avgRuns,worstCase,saveImg,new SelectionSort(),"Graph","Selection Sort Time","Array Size","Time(ms)");
    }
    public static void graphInsertionSortTime(int runCount, int increment, int avgRuns, boolean worstCase, boolean saveImg){
        GraphAlgorithmTime.makeGraph(runCount,increment,avgRuns,worstCase,saveImg,new InsertionSort(),"Graph","Insertion Sort Time","Array Size","Time(ms)");
    }
    public static Dataset graphMergeSortDif(int runCount, int increment,int avgRuns,boolean worstCase, boolean saveImg,boolean showGraph){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = increment; i <= runCount; i+=increment) {
            measureMergeSortTimeDif(i,x,y,avgRuns,worstCase);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d = new Dataset(x,y,"Merge sort time dif",false);
        LinearGraph graph = new LinearGraph("Graph","Merge sort time multi threaded vs single speed comparison" ,"Array size","Multi thread lead(%)",d,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
        return d;
    }

    /**
     * Runs merge sort single threaded and multithreaded multiple times and finds the average difference between the two
     * @param arraySize Size of the array to sort
     * @param sortSize Array to put data point of array sizes into
     * @param percentageBeat Array to put data point of percentages into
     * @param runCount How many times to average the percentage
     */
    public static void measureMergeSortTimeDif(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> percentageBeat, int runCount,boolean worstCase) {
        double percentSum = 0;
        for (int i = 0; i < runCount; i++) {
            ArrayList<Integer> list = MainApp.getArray(arraySize,worstCase);
            ArrayList<Integer> list1 = (ArrayList<Integer>) list.clone();

            //start timer for single threaded
            double singleStart = System.nanoTime() / 1000000.0;

            //start sorting
            (new MergeSingle()).sort(list);

            //end timer
            double singleEnd = System.nanoTime() / 1000000.0;

            //get time spent
            double totalTimeSingle = singleEnd - singleStart;

            //start time on multi
            double multiStart = System.nanoTime() / 1000000;

            //start sorting using multithreaded
            (new MergeMulti()).sort(list);

            //end multi time
            double multiEnd = System.nanoTime() / 1000000.0;

            //get time spent sorting
            double totalTimeMulti = multiEnd - multiStart;

            //print time spent on multi
            /*System.out.println("Total multi: "+totalTimeMulti);

            //print time spend on single
            System.out.println("Total single: "+totalTimeSingle);

            //print time diff
            System.out.println("Total difference(single-multi): "+(totalTimeSingle-totalTimeMulti));*/

            //get the percentage
            double percentage = ((totalTimeSingle - totalTimeMulti) / totalTimeMulti);

            //round percentage)
            double roundedPercent = Math.round(percentage * 100.0) / 100.0;
            percentSum += roundedPercent;
        }

        sortSize.add((double) arraySize);
        double avg = percentSum/runCount;
        percentageBeat.add(avg);
        //print rounded percentage
        /*System.out.println("Multi is "+roundedPercent+"% faster than single threaded");*/
    }
}

package Graphing;

import Sort.MergeSort;
import Main.MainApp;
import Sort.SelectionSort;
import com.sun.tools.javac.Main;

import java.util.ArrayList;

public class CreateGraphs {
    public static void graphAll(int arraySizeCount,int increment,int avgRuns,boolean saveToPng,boolean showGraph){
        ArrayList<Dataset> data = new ArrayList<>();

        ArrayList<Double> selectionX = new ArrayList<>();
        ArrayList<Double> selectionY = new ArrayList<>();
        graphSelectionSortTime(arraySizeCount,increment,avgRuns,selectionX,selectionY,false,false);
        Dataset selectionSortData = new Dataset(selectionX,selectionY,"Selection sort",false);
        data.add(selectionSortData);

        ArrayList<Double> insertionX = new ArrayList<>();
        ArrayList<Double> insertionY = new ArrayList<>();
        graphInsertionSortTime(arraySizeCount,increment,avgRuns,insertionX,insertionY,false,false);
        Dataset insertionSortData = new Dataset(insertionX,insertionY,"Insertion sort",false);
        data.add(insertionSortData);

        ArrayList<Double> mergeMultiX = new ArrayList<>();
        ArrayList<Double> mergeMultiY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,false,mergeMultiX,mergeMultiY,false,false);
        Dataset mergeMultiData = new Dataset(mergeMultiX,mergeMultiY,"Merge sort(multi threaded)",true);
        data.add(mergeMultiData);

        ArrayList<Double> mergeSingleX = new ArrayList<>();
        ArrayList<Double> mergeSingleY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,false,mergeSingleX,mergeSingleY,false,false);
        Dataset mergeSingleData = new Dataset(mergeSingleX,mergeSingleY,"Merge sort(single threaded)",true);
        data.add(mergeSingleData);

        LinearGraph graph = new LinearGraph("Graph","Sorting algorithms compared","Array Size","Sort delay(ms)",data,saveToPng);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMerges(int arraySizeCount,int increment,int avgRuns,boolean saveToPng,boolean showGraph){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Dataset> data = new ArrayList<>();


        ArrayList<Double> mergeMultiX = new ArrayList<>();
        ArrayList<Double> mergeMultiY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,true,mergeMultiX,mergeMultiY,false,false);
        Dataset mergeMultiXData = new Dataset(mergeMultiX,mergeMultiY,"Merge sort(multi-threaded)",true);
        data.add(mergeMultiXData);

        ArrayList<Double> mergeSingleX = new ArrayList<>();
        ArrayList<Double> mergeSingleY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,false,mergeSingleX,mergeSingleY,false,false);
        Dataset mergeSingleData = new Dataset(mergeSingleX,mergeSingleY,"Merge sort(single-threaded)",true);
        data.add(mergeSingleData);

        LinearGraph graph = new LinearGraph("Graph","Merge sort algorithm single threaded vs multi compared","Array Size","Sort delay(ms)",data,saveToPng);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMergeSortTime(int runCount, int increment,int avgRuns,boolean multiThreaded,ArrayList<Double> x,ArrayList<Double>y,boolean saveImg,boolean showGraph){
        for (int i = increment; i <= runCount; i+=increment) {
            measureMergeSortTime(i,x,y,avgRuns,multiThreaded);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d = new Dataset(x,y,"Merge sort",true);
        LinearGraph graph = new LinearGraph("Graph","Merge sort time \nMultithreaded: "+multiThreaded,"Array size","Time(ms)",d,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphSelectionSortTime(int runCount, int increment,int avgRuns,ArrayList<Double> x,ArrayList<Double>y,boolean saveImg,boolean showGraph){
        for (int i = increment; i <= runCount; i+=increment) {
            measureSelectionSortTime(i,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d =new Dataset(x,y,"Selection sort",false);
        LinearGraph graph = new LinearGraph("Graph","Selection sort time","Array size","Time(ms)", d,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphInsertionSortTime(int runCount, int increment,int avgRuns,ArrayList<Double> x,ArrayList<Double>y,boolean saveImg,boolean showGraph){
        for (int i = increment; i <= runCount; i+=increment) {
            measureInsertsionSortTime(i,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d = new Dataset(x,y,"Insertion sort",false);
        LinearGraph graph = new LinearGraph("Graph","Insertion sort time","Array size","Time(ms)",d,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMergeSortDif(int runCount, int increment,int avgRuns,boolean saveImg,boolean showGraph){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = increment; i <= runCount; i+=increment) {
            measureMergeSortTimeDif(i,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        Dataset d = new Dataset(x,y,"Merge sort time dif",false);
        LinearGraph graph = new LinearGraph("Graph","Merge sort time vs single speed" ,"Array size","Merge sort multi vs single(%)",d,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }

    /**
     * Runs merge sort single threaded and multithreaded multiple times and finds the average difference between the two
     * @param arraySize Size of the array to sort
     * @param sortSize Array to put data point of array sizes into
     * @param percentageBeat Array to put data point of percentages into
     * @param runCount How many times to average the percentage
     */
    public static void measureMergeSortTimeDif(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> percentageBeat, int runCount) {
        double percentSum = 0;
        for (int i = 0; i < runCount; i++) {
            ArrayList<Integer> list = MainApp.getArray(arraySize);
            ArrayList<Integer> list1 = (ArrayList<Integer>) list.clone();

            //start timer for single threaded
            double singleStart = System.nanoTime() / 1000000.0;

            //start sorting
            list1 = MergeSort.sort(list1, false, false);

            //end timer
            double singleEnd = System.nanoTime() / 1000000.0;

            //get time spent
            double totalTimeSingle = singleEnd - singleStart;

            //start time on multi
            double multiStart = System.nanoTime() / 1000000;

            //start sorting using multithreaded
            list = MergeSort.sort(list, true, false);

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
            double percentage = (((double) totalTimeSingle - (double) totalTimeMulti) / (double) totalTimeMulti);

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
    public static void measureMergeSortTime(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> times, int runCount, boolean multiThreaded){
        double timeSum = 0;
        for(int i =0;i<runCount;i++){
            ArrayList<Integer> list = MainApp.getArray(arraySize);
            long singleStart = System.nanoTime()/1000000;
            list = MergeSort.sort(list, multiThreaded, false);
            long end = System.nanoTime()/1000000;
            long dif=end-singleStart;
            timeSum+=dif;
        }
        sortSize.add((double)arraySize);
        times.add(timeSum/runCount);
    }
    /**
     * Runs insertion sort and finds the average run time
     * @param arraySize Size of the array to sort
     * @param sortSize Array to put data point of array sizes into
     * @param runCount How many times to average the percentage
     */
    public static void measureInsertsionSortTime(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> times, int runCount) {
        double timesSum = 0;
        for (int i = 0; i < runCount; i++) {
            ArrayList<Integer> list = MainApp.getArray(arraySize);

            //start time on multi
            long multiStart = System.nanoTime() / 1000000;

            //start sorting using multithreaded
            list = SelectionSort.sort(list);

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
        sortSize.add((double)arraySize);
        times.add(timesSum/runCount);
    }
    /**
     * Runs selection sort and finds the average run time
     * @param arraySize Size of the array to sort
     * @param sortSize Array to put data point of array sizes into
     * @param runCount How many times to average the percentage
     */
    public static void measureSelectionSortTime(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> times, int runCount) {
        double timesSum = 0;
        for (int i = 0; i < runCount; i++) {
            ArrayList<Integer> list = MainApp.getArray(arraySize);

            //start time on multi
            long multiStart = System.nanoTime() / 1000000;

            //start sorting using multithreaded
            list = SelectionSort.sort(list);

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
        sortSize.add((double)arraySize);
        times.add(timesSum/runCount);
    }
}

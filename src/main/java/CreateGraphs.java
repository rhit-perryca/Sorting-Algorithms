import java.util.ArrayList;

public class CreateGraphs {
    public static void graphAll(int arraySizeCount,int increment,int avgRuns,boolean saveToPng,boolean showGraph){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<ArrayList<Double>> xValues = new ArrayList<>();
        ArrayList<ArrayList<Double>> yValues = new ArrayList<>();

        ArrayList<Double> selectionX = new ArrayList<>();
        ArrayList<Double> selectionY = new ArrayList<>();
        graohSelectionSortTime(arraySizeCount,increment,avgRuns,selectionX,selectionY,false,false);
        xValues.add(selectionX);
        yValues.add(selectionY);
        names.add("Selection sort");

        ArrayList<Double> insertionX = new ArrayList<>();
        ArrayList<Double> insertionY = new ArrayList<>();
        graphInsertionSortTime(arraySizeCount,increment,avgRuns,insertionX,insertionY,false,false);
        xValues.add(insertionX);
        yValues.add(insertionY);
        names.add("Insertion sort");


        ArrayList<Double> mergeMultiX = new ArrayList<>();
        ArrayList<Double> mergeMultiY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,false,mergeMultiX,mergeMultiY,false,false);
        xValues.add(mergeMultiX);
        yValues.add(mergeMultiY);
        names.add("Merge sort(multi-threaded)");

        ArrayList<Double> mergeSingleX = new ArrayList<>();
        ArrayList<Double> mergeSingleY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,false,mergeSingleX,mergeSingleY,false,false);
        xValues.add(mergeSingleX);
        yValues.add(mergeSingleY);
        names.add("Merge sort(single-threaded)");

        LinearGraph graph = new LinearGraph("Graph","Array Size","Sort delay(ms)","Sorting algorithms compared",names,xValues,yValues,saveToPng);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMerges(int arraySizeCount,int increment,int avgRuns,boolean saveToPng,boolean showGraph){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<ArrayList<Double>> xValues = new ArrayList<>();
        ArrayList<ArrayList<Double>> yValues = new ArrayList<>();


        ArrayList<Double> mergeMultiX = new ArrayList<>();
        ArrayList<Double> mergeMultiY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,true,mergeMultiX,mergeMultiY,false,false);
        xValues.add(mergeMultiX);
        yValues.add(mergeMultiY);
        names.add("Merge sort(multi-threaded)");

        ArrayList<Double> mergeSingleX = new ArrayList<>();
        ArrayList<Double> mergeSingleY = new ArrayList<>();
        graphMergeSortTime(arraySizeCount,increment,avgRuns,false,mergeSingleX,mergeSingleY,false,false);
        xValues.add(mergeSingleX);
        yValues.add(mergeSingleY);
        names.add("Merge sort(single-threaded)");

        LinearGraph graph = new LinearGraph("Graph","Array Size","Sort delay(ms)","Merge sort algorithm single threaded vs multi compared",names,xValues,yValues,saveToPng);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMergeSortTime(int runCount, int increment,int avgRuns,boolean multiThreaded,ArrayList<Double> x,ArrayList<Double>y,boolean saveImg,boolean showGraph){
        for (int i = 1; i <= runCount; i++) {
            measureMergeSortTime(i*increment,x,y,avgRuns,multiThreaded);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        LinearGraph graph = new LinearGraph("Graph","Array size","Time(ms)","Merge sort time \nMultithreaded: "+multiThreaded,"Line", x, y,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graohSelectionSortTime(int runCount, int increment,int avgRuns,ArrayList<Double> x,ArrayList<Double>y,boolean saveImg,boolean showGraph){
        for (int i = 1; i <= runCount; i++) {
            measureSelectionSortTime(i*increment,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        LinearGraph graph = new LinearGraph("Graph","Array size","Time(ms)","Selection sort time","Line", x, y,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphInsertionSortTime(int runCount, int increment,int avgRuns,ArrayList<Double> x,ArrayList<Double>y,boolean saveImg,boolean showGraph){
        for (int i = 1; i <= runCount; i++) {
            measureSelectionSortTime(i*increment,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        LinearGraph graph = new LinearGraph("Graph","Array size","Time(ms)","Insertion sort time","Line", x, y,saveImg);
        if(showGraph) {
            graph.pack();
            graph.setVisible(true);
        }
    }
    public static void graphMergeSortDif(int runCount, int increment,int avgRuns,boolean saveImg,boolean showGraph){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 1; i <= runCount; i++) {
            measureMergeSortTimeDif(i*increment,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        LinearGraph graph = new LinearGraph("Graph","Array size","Merge sort multi vs single(%)","Merge sort time vs single speed" ,"Line", x, y,saveImg);
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
            ArrayList<Integer> list = Main.getArray(arraySize);
            ArrayList<Integer> list1 = (ArrayList<Integer>) list.clone();

            //start timer for single threaded
            long singleStart = System.nanoTime() / 1000000;

            //start sorting
            list1 = MergeSort.sort(list1, false, false);

            //end timer
            long singleEnd = System.nanoTime() / 1000000;

            //get time spent
            long totalTimeSingle = singleEnd - singleStart;

            //start time on multi
            long multiStart = System.nanoTime() / 1000000;

            //start sorting using multithreaded
            list = MergeSort.sort(list, true, false);

            //end multi time
            long multiEnd = System.nanoTime() / 1000000;

            //get time spent sorting
            long totalTimeMulti = multiEnd - multiStart;

            //print time spent on multi
            /*System.out.println("Total multi: "+totalTimeMulti);

            //print time spend on single
            System.out.println("Total single: "+totalTimeSingle);

            //print time diff
            System.out.println("Total difference(single-multi): "+(totalTimeSingle-totalTimeMulti));*/

            //get the percentage
            double percentage = (((double) totalTimeSingle - (double) totalTimeMulti) / (double) totalTimeMulti) * 100;

            //round percentage)
            double roundedPercent = Math.round(percentage * 100.0) / 100.0;
            percentSum += roundedPercent;
        }

        sortSize.add((double) arraySize);
        percentageBeat.add(percentSum / runCount);
        //print rounded percentage
        /*System.out.println("Multi is "+roundedPercent+"% faster than single threaded");*/
    }
    public static void measureMergeSortTime(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> times, int runCount, boolean multiThreaded){
        double timeSum = 0;
        for(int i =0;i<runCount;i++){
            ArrayList<Integer> list = Main.getArray(arraySize);
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
            ArrayList<Integer> list = Main.getArray(arraySize);

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
            ArrayList<Integer> list = Main.getArray(arraySize);

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

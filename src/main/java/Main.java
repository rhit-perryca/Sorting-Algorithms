import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //graphMergeSortTime(200,100,100,true);
        //graphMergeSortTime(200,100,100,false);
        graphMergeSortDif(200,100,10,true);
    }
    public static void graphMergeSortTime(int runCount, int increment,int avgRuns,boolean multiThreaded,boolean saveImg){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 1; i <= runCount; i++) {
            measureTime(i*increment,x,y,avgRuns,multiThreaded);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        LinearGraph chart = new LinearGraph("Graph","Array size","Time(ms)","Merge sort time \nMultithreaded: "+multiThreaded,"Line", x, y,saveImg);
        chart.pack();
        chart.setVisible(true);
    }
    public static void graphMergeSortDif(int runCount, int increment,int avgRuns,boolean saveImg){
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        for (int i = 1; i <= runCount; i++) {
            measureTimeDif(i*increment,x,y,avgRuns);
            double percentDone = (double) i/(double) runCount;
            double roundedPercent = (Math.round(percentDone * 100.0) / 100.0)*100.0;
            System.out.println(roundedPercent+"%");
        }
        LinearGraph chart = new LinearGraph("Graph","Array size","Merge sort multi vs single(%)","Merge sort time vs single speed" ,"Line", x, y,saveImg);
        chart.pack();
        chart.setVisible(true);
    }
    public static void measureTime(int arraySize,ArrayList<Double> sortSize, ArrayList<Double> times,int runCount,boolean multiThreaded){
        double timeSum = 0;
        for(int i =0;i<runCount;i++){
            ArrayList<Integer> list = getArray(arraySize);
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
     * Runs merge sort single threaded and multithreaded multiple times and finds the average difference between the two
     * @param arraySize Size of the array to sort
     * @param sortSize Array to put data point of array sizes into
     * @param percentageBeat Array to put data point of percentages into
     * @param runCount How many times to average the percentage
     */
    public static void measureTimeDif(int arraySize, ArrayList<Double> sortSize, ArrayList<Double> percentageBeat, int runCount) {
        double percentSum = 0;
        for (int i = 0; i < runCount; i++) {
            ArrayList<Integer> list = getArray(arraySize);
            ArrayList<Integer> list1 = (ArrayList<Integer>) list.clone();

            //start timer for single threaded
            long singleStart = System.nanoTime() / 1000;

            //start sorting
            list1 = MergeSort.sort(list1, false, false);

            //end timer
            long singleEnd = System.nanoTime() / 1000;

            //get time spent
            long totalTimeSingle = singleEnd - singleStart;

            //start time on multi
            long multiStart = System.nanoTime() / 1000;

            //start sorting using multithreaded
            list = MergeSort.sort(list, true, false);

            //end multi time
            long multiEnd = System.nanoTime() / 1000;

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

    /**
     * Gets an array with numbers from 1-range
     * @param range Top end of numbers to generate
     * @return Array of numbers
     */
    public static ArrayList<Integer> getArray(int range) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= range; i++) {
            list.add(i);
        }
        shuffle(list);
        return list;
    }

    /**
     * Shuffles array
     * @param list array list to shuffle
     */
    private static void shuffle(ArrayList<Integer> list) {
        Random rnd = new Random();
        for (int i = 0; i < list.size(); i++) {
            int index = rnd.nextInt(list.size());
            int newNum = list.get(index);
            int oldNum = list.get(i);
            list.set(index, oldNum);
            list.set(i, newNum);
        }
    }

}

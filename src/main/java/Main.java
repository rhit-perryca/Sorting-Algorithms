import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> sizes = new ArrayList<>();
        ArrayList<Double> percents = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            measureTimeDif(i * 100, sizes, percents, 5);
        }
        GraphDifs chart = new GraphDifs("Graph", sizes, percents);
        chart.pack();
        chart.setVisible(true);
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

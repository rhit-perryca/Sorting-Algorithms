package Main;

import Graphing.CreateGraphs;

import java.util.ArrayList;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        //graphMergeSortTime(200,100,100,true);
        //graphMergeSortTime(200,100,100,false);
        int runCount =20000;
        int increment=100;
        int avg=10;
        boolean worstCase=true;
        boolean saveImg=true;
        CreateGraphs.graphMerges(runCount,increment,avg,false,false);
    }


    /**
     * Gets an array with numbers from 1-range
     *
     * @param range Top end of numbers to generate
     * @return Array of numbers
     */
    public static ArrayList<Integer> getArray(int range, boolean worstCase) {
        if (worstCase) {
            ArrayList<Integer> list = new ArrayList<>();
            for(int i = range;i>=1;i--){
                list.add(i);
            }
            return list;
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 1; i <= range; i++) {
                list.add(i);
            }
            shuffle(list);
            return list;
        }
    }

    /**
     * Shuffles array
     *
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

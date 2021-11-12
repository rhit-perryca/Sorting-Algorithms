package Sort;

import java.util.ArrayList;

/**
 * Thread class to multi thread merge sort
 */
public class SortingThread extends Thread {
    ArrayList<Integer> list;
    boolean logging;

    /**
     * Constructor
     * @param list list to sort
     * @param logging logging
     */
    public SortingThread(ArrayList<Integer> list,boolean logging) {
        this.list = list;
        this.logging=logging;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    /**
     * Run merge sort on thread
     */
    @Override
    public void run() {
        list=MergeSort.sort(list,false,logging
        );
    }
}

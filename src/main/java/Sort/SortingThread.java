package Sort;

import Sort.Merge.MergeMulti;
import Sort.Merge.MergeSingle;
import Sort.Merge.MergeSort;

import java.util.ArrayList;

/**
 * Thread class to multi thread merge sort
 */
public class SortingThread extends Thread {
    ArrayList<Integer> list;

    /**
     * Constructor
     * @param list list to sort
     *
     */
    public SortingThread(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    /**
     * Run merge sort on thread
     */
    @Override
    public void run() {
        MergeSingle single = new MergeSingle();
        list=single.sort(list);
    }
}

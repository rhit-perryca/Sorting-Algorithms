package Sort.Merge;

import Sort.Merge.MergeSort;
import Sort.SortingThread;

import java.util.ArrayList;

public class MergeMulti extends MergeSort {

    public MergeMulti(){
        name="MergeSort(Multi)";
    }
    public static ArrayList<Integer> sortHelper(ArrayList<Integer>in){
        if (in.size() != 1) {

            ArrayList<Integer> f = new ArrayList<>();
            ArrayList<Integer> l = new ArrayList<>();
            MergeMulti.splitArray(in, f, l);
                SortingThread fSort = new SortingThread(f);
                SortingThread lSort = new SortingThread(l);
                fSort.start();
                lSort.start();
                try {
                    fSort.join();
                    lSort.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                in = MergeMulti.merge(f, l);
                return in;
        }
        return in;
    }
    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> in) {
        return MergeMulti.sortHelper(in);
    }

}

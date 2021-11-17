package Sort.Merge;

import java.util.ArrayList;

public class MergeSingle extends MergeSort {
    public MergeSingle(){
        name="MergeSort(Single)";
    }
    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> in) {
        if (in.size() != 1) {
            ArrayList<Integer> f = new ArrayList<>();
            ArrayList<Integer> l = new ArrayList<>();
            splitArray(in, f, l);
            f=sort(f);
            l=sort(l);

            in = merge(f, l);
            return in;
        }
        return in;
    }
}

package Sort.Merge;

import Sort.SortingAlgorithm;

import java.util.ArrayList;

/**
 * Class to use merge sort to sort array of integers
 */
public abstract class MergeSort extends SortingAlgorithm {
    /**
     * Merge two arrays
     * @param f first array
     * @param l last array
     * @return List of f and l combined and sorted
     */
    public static ArrayList<Integer> merge(ArrayList<Integer> f, ArrayList<Integer> l) {
        ArrayList<Integer> list = new ArrayList<>();
        int fIndex=0;
        int lIndex=0;
        while (fIndex<f.size() && lIndex<l.size()) {
            if (f.get(fIndex) < l.get(lIndex)) {
                list.add(f.get(fIndex));
                fIndex++;
            } else if (f.get(fIndex) > l.get(lIndex)) {
                list.add(l.get(lIndex));
                lIndex++;
            } else if (f.get(fIndex) == l.get(lIndex)) {
                list.add(f.get(fIndex));
                list.add(l.get(lIndex));
                lIndex++;
                fIndex++;
            }
        }
        if(fIndex<f.size()){
            for(int i = fIndex;i<f.size();i++){
                list.add(f.get(i));
            }
        }if(lIndex<l.size()){
            for(int i = lIndex;i<l.size();i++){
                list.add(l.get(i));
            }
        }
        return list;
        //*/
    }

    public MergeSort(){
        name="MergeSort";
    }
    public abstract ArrayList<Integer> sort(ArrayList<Integer> in);

    /**
     * Splits array down the middle into two arrays
     * @param in input array
     * @param first first half of array
     * @param last last part of array
     */
    public static void splitArray(ArrayList<Integer> in, ArrayList<Integer> first, ArrayList<Integer> last) {
        int index = 0;
        int splitIndex = (in.size() / 2) - 1;
        for (int i = 0; i <= splitIndex; i++) {
            first.add(in.get(i));
            index = i;
        }
        index++;
        for (int i = index; i < in.size(); i++) {
            last.add(in.get(i));
        }
    }
}

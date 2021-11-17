package Sort;

import java.util.ArrayList;

public class SelectionSort extends SortingAlgorithm{
    public ArrayList<Integer> sort(ArrayList<Integer> in){
        ArrayList<Integer> list = new ArrayList<>();
        while(in.size()!=0){
            list.add(getSmallest(in));
        }
        return list;
    }
    public SelectionSort(){
        name="SelectionSort";
    }
    public static int getSmallest(ArrayList<Integer>in){
        int min=Integer.MAX_VALUE;
        for(int i=0;i<in.size();i++){
            if(in.get(i)<min){
                min=in.get(i);
            }
        }
        in.remove((Integer)min);
        return min;
    }
}

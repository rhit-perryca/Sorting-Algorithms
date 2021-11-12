package Sort;

import java.util.ArrayList;

public class InsertionSort {
    public static ArrayList<Integer> sort(ArrayList<Integer>in){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i<in.size();i++){
            insert(list,in.get(i));
        }
        return list;
    }
    public static ArrayList<Integer> insert(ArrayList<Integer> in,int value){
        int prev=Integer.MIN_VALUE;
        for(int i=0;i<in.size();i++){
            int cur=in.get(i);
            if(value>=prev&&value<=cur){
                in.add(i,value);
                return in;
            }

            prev=cur;
        }
        in.add(value);
        return in;
    }
}

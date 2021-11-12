import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        ArrayList<Integer> list = randomArray();
        ArrayList<Integer> list1= (ArrayList<Integer>) list.clone();


        long singleStart = System.nanoTime()/1000;
        list1=MergeSort.sort(list1,false);
        long singleEnd = System.nanoTime()/1000;
        long totalTimeSingle=singleEnd-singleStart;

        long multiStart = System.nanoTime()/1000;
        list=MergeSort.sort(list,true);
        long multiEnd   = System.nanoTime()/1000;
        long totalTimeMulti = multiEnd - multiStart;
        System.out.println(totalTimeMulti-totalTimeSingle);
    }
    public static ArrayList<Integer> randomArray(){
        Random rnd = new Random();
        ArrayList<Integer> arr = new ArrayList<>();
        int size=rnd.nextInt(11)+10;
        for(int i=0;i<size;i++){
            arr.add(rnd.nextInt(21)+3);
        }
        return arr;
    }
    public static boolean isSorted(ArrayList<Integer> in) {
        int prev = Integer.MIN_VALUE;
        for (int i : in) {
            if (i < prev) {
                return false;
            }
            prev = i;
        }
        return true;
    }
    public static int getLast(ArrayList<Integer>in){
        return in.get(in.size()-1);
    }

}

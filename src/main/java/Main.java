import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        ArrayList<Integer> list = randomArray(10000000);
        ArrayList<Integer> list1= (ArrayList<Integer>) list.clone();

        //start timer for single threaded
        long singleStart = System.nanoTime()/1000;

        //start sorting
        list1=MergeSort.sort(list1,false,false);

        //end timer
        long singleEnd = System.nanoTime()/1000;

        //get time spent
        long totalTimeSingle=singleEnd-singleStart;

        //start time on multi
        long multiStart = System.nanoTime()/1000;

        //start sorting using multithreaded
        list=MergeSort.sort(list,true,false);

        //end multi time
        long multiEnd   = System.nanoTime()/1000;

        //get time spent sorting
        long totalTimeMulti = multiEnd - multiStart;

        //print time spent on multi
        System.out.println("Total multi: "+totalTimeMulti);

        //print time spend on single
        System.out.println("Total single: "+totalTimeSingle);

        //print time diff
        System.out.println("Total difference(single-multi): "+(totalTimeSingle-totalTimeMulti));

        //get the percentage
        double percentage = (((double) totalTimeSingle-(double) totalTimeMulti)/(double)totalTimeMulti)*100;

        //round percentage)
        double roundedPercent = Math.round(percentage * 100.0) / 100.0;

        //print rounded percentage
        System.out.println("Multi is "+roundedPercent+"% faster than single threaded");
    }
    public static ArrayList<Integer> randomArray(int range){
        ArrayList<Integer> list  = new ArrayList<>();
        for(int i = 1; i <= range;i++){
            list.add(i);
        }
        shuffle(list);
        return list;
    }

    private static void shuffle(ArrayList<Integer> list) {
        Random rnd = new Random();
        for(int i = 0; i < list.size();i++){
            int index = rnd.nextInt(list.size());
            int newNum = list.get(index);
            int oldNum = list.get(i);
            list.set(index,oldNum);
            list.set(i,newNum);
        }
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

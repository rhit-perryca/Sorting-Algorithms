import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = randomArray();
        System.out.println(list);
        list=SelectionSort.sort(list);
        System.out.println(list);
    }
    public static ArrayList<Integer> randomArray(){
        Random rnd = new Random();
        ArrayList<Integer> arr = new ArrayList<>();
        int size=rnd.nextInt(21)+3;
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

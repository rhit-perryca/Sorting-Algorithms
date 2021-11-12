import java.util.ArrayList;

public class SortingThread extends Thread {
    ArrayList<Integer> list;
    boolean logging;

    public SortingThread(ArrayList<Integer> list,boolean logging) {
        this.list = list;
        this.logging=logging;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    @Override
    public void run() {
        list=MergeSort.sort(list,false,logging);
    }
}

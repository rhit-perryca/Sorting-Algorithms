import java.util.ArrayList;

public class SortingThread extends Thread {
    ArrayList<Integer> list;

    public SortingThread(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    @Override
    public void run() {
        list=MergeSort.sort(list,false);
    }
}

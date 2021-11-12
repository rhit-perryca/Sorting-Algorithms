import java.util.ArrayList;

public class MergeSort {
    public static ArrayList<Integer> merge(ArrayList<Integer> f, ArrayList<Integer> l) {
        ArrayList<Integer> list = new ArrayList<>();
        while (f.size() != 0 && l.size() != 0) {
            if (f.get(0) < l.get(0)) {
                list.add(f.get(0));
                f.remove(0);
            } else if (f.get(0) > l.get(0)) {
                list.add(l.get(0));
                l.remove(0);
            } else if (f.get(0) == l.get(0)) {
                list.add(f.get(0));
                list.add(l.get(0));
                l.remove(0);
                f.remove(0);
            }
        }
        if (f.size() != 0) {
            list.addAll(f);
        }
        if (l.size() != 0) {
            list.addAll(l);
        }
        return list;
    }

    public static ArrayList<Integer> sort(ArrayList<Integer> in,boolean multiThreaded) {
        if (in.size() != 1) {
            ArrayList<Integer> f = new ArrayList<>();
            ArrayList<Integer> l = new ArrayList<>();
            splitArray(in, f, l);
            if(multiThreaded){
                long timeS=System.nanoTime();
                SortingThread fSort = new SortingThread(f);
                SortingThread lSort = new SortingThread(l);

                long timeE=System.nanoTime();
                long dif =timeE-timeS;
                fSort.start();
                lSort.start();
                try {
                    fSort.join();
                    lSort.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                f=sort(f,false);
                l=sort(l,false);
            }

            in = merge(f, l);
            return in;
        }
        return in;
    }

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

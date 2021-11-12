import java.util.ArrayList;

public class MergeSort {
    public static ArrayList<Integer> merge(ArrayList<Integer> f, ArrayList<Integer> l,boolean logging) {
        /*
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
        */
        ///*
        ArrayList<Integer> list = new ArrayList<>();
        if(logging)
            System.out.println("Merging "+l+" and "+f);
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
            if(logging)
                System.out.println("Merge: "+list);
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
        if(logging)
            System.out.println("Merged into "+list+"\n");
        return list;
        //*/
    }

    public static ArrayList<Integer> sort(ArrayList<Integer> in,boolean multiThreaded,boolean logging) {
        if (in.size() != 1) {
            ArrayList<Integer> f = new ArrayList<>();
            ArrayList<Integer> l = new ArrayList<>();
            if(logging)
                System.out.println("Splitting "+in+'\n');
            splitArray(in, f, l);
            if(multiThreaded){
                SortingThread fSort = new SortingThread(f,logging);
                SortingThread lSort = new SortingThread(l,logging);
                fSort.start();
                lSort.start();
                try {
                    fSort.join();
                    lSort.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                if(logging)
                    System.out.println("Sorting "+f+'\n');
                f=sort(f,false,logging);
                if(logging)
                    System.out.println("Sorting "+l+'\n');
                l=sort(l,false,logging);
            }

            in = merge(f, l,logging);
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

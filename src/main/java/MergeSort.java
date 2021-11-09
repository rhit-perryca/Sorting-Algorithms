import java.util.ArrayList;

public class MergeSort {
    public static ArrayList<Integer> merge(ArrayList<Integer> f, ArrayList<Integer>l){
        ArrayList<Integer> list = new ArrayList<>();
        while(f.size()!=0&&l.size()!=0){
            if(f.get(0)<l.get(0)){
                list.add(f.get(0));
                f.remove(0);
            }
            else if(f.get(0)>l.get(0)){
                list.add(l.get(0));
                l.remove(0);
            }
            else if(f.get(0)==l.get(0)){
                list.add(f.get(0));
                list.add(l.get(0));
                l.remove(0);
                f.remove(0);
            }
        }
        if(f.size()!=0){
            list.addAll(f);
        }
        if(l.size()!=0){
            list.addAll(l);
        }
        return list;
    }
    public static ArrayList<Integer> sort(ArrayList<Integer> in) {
        if (in.size() != 1) {
            ArrayList<Integer> f=new ArrayList<>();
            ArrayList<Integer> l=new ArrayList<>();
            splitArray(in,f,l);
            f=sort(f);
            l=sort(l);
            return merge(f,l);
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

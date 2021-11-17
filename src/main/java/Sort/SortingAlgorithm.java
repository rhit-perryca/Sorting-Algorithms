package Sort;

import java.util.ArrayList;

public abstract class SortingAlgorithm {

    public String getName() {
        return name;
    }

    protected String name;
    public abstract ArrayList<Integer> sort(ArrayList<Integer> in);

}

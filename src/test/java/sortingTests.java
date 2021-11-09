import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class sortingTests {

    @Test
    void insertionSort() {
        ArrayList<Integer> list= new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(14);
        list.add(1);
        ArrayList<Integer> actual=new ArrayList<>();
        actual.add(1);
        actual.add(4);
        actual.add(5);
        actual.add(6);
        actual.add(14);
        list=InsertionSort.sort(list);
        assertEquals(actual,list);
    }
    @Test
    void selectionSort() {
        ArrayList<Integer> list= new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(14);
        list.add(1);
        ArrayList<Integer> actual=new ArrayList<>();
        actual.add(1);
        actual.add(4);
        actual.add(5);
        actual.add(6);
        actual.add(14);
        list=SelectionSort.sort(list);
        assertEquals(actual,list);
    }
    @Test
    void mergeSort() {
        ArrayList<Integer> list= new ArrayList<>();
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(14);
        list.add(1);
        ArrayList<Integer> actual=new ArrayList<>();
        actual.add(1);
        actual.add(4);
        actual.add(5);
        actual.add(6);
        actual.add(14);
        list=MergeSort.sort(list);
        assertEquals(actual,list);
    }
}
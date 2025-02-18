package Parallel_Sorting;

public class MergeSort implements SortingAlgorithm {
    @Override
    public void sort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            sort(array, low, mid);
            sort(array, mid + 1, high);
            SortUtils.merge(array, low, mid, high);
        }
    }
}


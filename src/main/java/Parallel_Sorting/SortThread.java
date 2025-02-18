package Parallel_Sorting;

public class SortThread implements Runnable {
    private final int[] array;
    private final int startIndex;
    private final int endIndex;
    private final SortingAlgorithm algorithm;

    public SortThread(int[] array, int startIndex, int endIndex, SortingAlgorithm algorithm) {
        this.array = array;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.algorithm = algorithm;
    }

    @Override
    public void run() {
        algorithm.sort(array, startIndex, endIndex);
    }
}
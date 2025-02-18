package Parallel_Sorting;

public class SortService {
    private static final int NUMS_COUNT = 100;
    private final SortingAlgorithm sortingAlgorithm;

    public SortService(SortingAlgorithm sortingAlgorithm) {
        this.sortingAlgorithm = sortingAlgorithm;
    }

    public void parallelSort(int[] array, int numThreads) {
        Thread[] threads = new Thread[numThreads];
        int segmentLength = NUMS_COUNT / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * segmentLength;
            int endIndex = (i == numThreads - 1) ? NUMS_COUNT - 1 : (startIndex + segmentLength - 1);
            threads[i] = new Thread(new SortThread(array, startIndex, endIndex, sortingAlgorithm));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        SortUtils.mergeSortedSegments(array, numThreads, NUMS_COUNT);
    }
}
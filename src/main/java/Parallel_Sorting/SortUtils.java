package Parallel_Sorting;

public class SortUtils {

    public static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;


        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        System.arraycopy(temp, 0, array, left, temp.length);
    }


    public static void mergeSortedSegments(int[] array, int numThreads, int numsCount) {
        int segmentSize = numsCount / numThreads;
        int[] temp = new int[numsCount];
        int[] indices = new int[numThreads];

        for (int i = 0; i < numThreads; i++) {
            indices[i] = i * segmentSize;
        }

        for (int i = 0; i < numsCount; i++) {
            int minIndex = -1;
            int minValue = Integer.MAX_VALUE;

            for (int j = 0; j < numThreads; j++) {
                if (indices[j] < (j == numThreads - 1 ? numsCount : (j + 1) * segmentSize) && array[indices[j]] < minValue) {
                    minValue = array[indices[j]];
                    minIndex = j;
                }
            }

            temp[i] = minValue;
            indices[minIndex]++;
        }

        System.arraycopy(temp, 0, array, 0, numsCount);
    }
}
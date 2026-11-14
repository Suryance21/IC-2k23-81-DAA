import java.util.Arrays;

public class MergeSort {
    static int[] mergeSort(int[] input) {
        if (input.length <= 1)
            return input.clone();

        int mid = input.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(input, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(input, mid, input.length));
        int[] result = new int[input.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            result[k++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        while (i < left.length)
            result[k++] = left[i++];
        while (j < right.length)
            result[k++] = right[j++];
        return result;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] sample = { 8, 3, 5, 4, 7, 6, 1, 2 };
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("Merge sort: " + Arrays.toString(mergeSort(sample)));
    }
}

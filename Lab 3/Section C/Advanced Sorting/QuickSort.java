import java.util.Arrays;

public class QuickSort {

    static int[] quickSort(int[] input) {
        int[] a = input.clone();
        quick(a, 0, a.length - 1);
        return a;
    }

    private static void quick(int[] a, int low, int high) {
        if (low < high) {
            int p = partition(a, low, high);
            quick(a, low, p - 1);
            quick(a, p + 1, high);
        }
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high], i = low - 1;
        for (int j = low; j < high; j++)
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        swap(a, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int[] sample = { 8, 3, 5, 4, 7, 6, 1, 2 };
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("Quick sort (last-element pivot): " + Arrays.toString(quickSort(sample)));
    }
}

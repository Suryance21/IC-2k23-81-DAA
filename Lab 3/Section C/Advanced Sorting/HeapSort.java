import java.util.Arrays;

public class HeapSort {

    static int[] heapSort(int[] input) {
        int[] a = input.clone();
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(a, n, i);
        for (int i = n - 1; i > 0; i--) {
            swap(a, 0, i);
            heapify(a, i, 0);
        }
        return a;
    }

    private static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void heapify(int[] a, int n, int root) {
        int largest = root, l = 2 * root + 1, r = 2 * root + 2;
        if (l < n && a[l] > a[largest])
            largest = l;
        if (r < n && a[r] > a[largest])
            largest = r;
        if (largest != root) {
            swap(a, root, largest);
            heapify(a, n, largest);
        }
    }

    public static void main(String[] args) {
        int[] sample = { 8, 3, 5, 4, 7, 6, 1, 2 };
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("Heap sort: " + Arrays.toString(heapSort(sample)));
    }
}

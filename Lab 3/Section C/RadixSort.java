import java.util.Arrays;

public class RadixSort {
    // Stable counting sort for one decimal digit place.
    private static void countingSortByDigit(int[] a, int exp) {
        int n = a.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int value : a)
            count[(value / exp) % 10]++;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int digit = (a[i] / exp) % 10;
            output[--count[digit]] = a[i];
        }
        System.arraycopy(output, 0, a, 0, n);
        System.out.println("After place " + exp + ": " + Arrays.toString(a));
    }

    static int[] radixSort(int[] input) {
        int[] a = input.clone();
        for (int x : a)
            if (x < 0)
                throw new IllegalArgumentException("RadixSort accepts non-negative integers only.");
        int max = 0;
        for (int x : a)
            max = Math.max(max, x);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortByDigit(a, exp);
        return a;
    }

    public static void main(String[] args) {
        int[] sample = { 170, 45, 75, 90, 802, 24, 2, 66 };
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("Sorted: " + Arrays.toString(radixSort(sample)));
    }
}

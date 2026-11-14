import java.util.Arrays;

public class BubbleSort {
    static int[] bubbleSort(int[] input) {
        int[] a = input.clone();
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++)
                if (a[j] > a[j + 1]) { int t=a[j]; a[j]=a[j+1]; a[j+1]=t; }
            System.out.println("Bubble pass " + (i+1) + ": " + Arrays.toString(a));
        }
        return a;
    }

    public static void main(String[] args) {
        int[] sample={5,2,8,1,9};
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("\\nBUBBLE SORT"); System.out.println("Sorted: "+Arrays.toString(bubbleSort(sample)));
    }
}

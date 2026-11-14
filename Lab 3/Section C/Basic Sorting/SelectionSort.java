import java.util.Arrays;

public class SelectionSort {

    static int[] selectionSort(int[] input) {
        int[] a = input.clone();
        for (int i = 0; i < a.length - 1; i++) {
            int min=i;
            for (int j=i+1; j<a.length; j++) if (a[j]<a[min]) min=j;
            int t=a[i]; a[i]=a[min]; a[min]=t;
            System.out.println("Selection pass " + (i+1) + ": " + Arrays.toString(a));
        }
        return a;
    }

    public static void main(String[] args) {
        int[] sample={5,2,8,1,9};
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("\\nSELECTION SORT"); System.out.println("Sorted: "+Arrays.toString(selectionSort(sample)));
    }
}

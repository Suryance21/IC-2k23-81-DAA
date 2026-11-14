import java.util.Arrays;

public class InsertionSort {

    static int[] insertionSort(int[] input) {
        int[] a = input.clone();
        for (int i=1; i<a.length; i++) {
            int key=a[i], j=i-1;
            while (j>=0 && a[j]>key) { a[j+1]=a[j]; j--; }
            a[j+1]=key;
            System.out.println("Insertion pass " + i + ": " + Arrays.toString(a));
        }
        return a;
    }

    public static void main(String[] args) {
        int[] sample={5,2,8,1,9};
        System.out.println("Input: " + Arrays.toString(sample));
        System.out.println("\\nINSERTION SORT"); System.out.println("Sorted: "+Arrays.toString(insertionSort(sample)));
    }
}

import java.util.Arrays;

public class BinarySearch {
    // Returns the zero-based index, or -1 when target is absent.
    static int search(int[] sorted,int target) {
        int low=0,high=sorted.length-1;
        while(low<=high) {
            int mid=low+(high-low)/2;
            if(sorted[mid]==target) return mid;
            if(sorted[mid]<target) low=mid+1; else high=mid-1;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] data={2,5,8,12,16,23,38,45,56,72,91};
        System.out.println("Array: "+Arrays.toString(data));
        System.out.println("23 found at index: "+search(data,23));
        System.out.println("100 found at index: "+search(data,100));
    }
}

/**
 * Introspective sort class.
 * @author Jacob West
 * @version 1.0
 */
public class IntroSort {

    /**
     * Calls the recursive introSort method, and initializes the max
     * depth before switching to be 2 * log2(items.length).
     * @param <T> Type of item
     * @param items Array of items to be sorted
     */
    public static <T extends Comparable<T>> void introSort(T[] items) {
        if (items.length > 0) {
            int depth = (int) (2 * Math.floor(
                    Math.log10(items.length) / Math.log10(2)));
            introHelp(items, depth, 0, items.length - 1);
        }
    }
    
    /**
     * Recursive function. Uses quicksort initially, but switches to merge2
     * if enough calls are made.
     * @param <T> Type of item
     * @param items Array of items to be sorted
     * @param depth Maximum depth to use quicksort before using merge2
     * @param left Left half of subarray 
     * @param right Right half of subarray
     */
    private static <T extends Comparable<T>> void introHelp(
            T[] items, int depth, int left, int right) {
        if (left >= right) {
            return; // Recursion base case
        } else if (depth == 0) {
            MergeSortsImproved.mergeSort2(items, left, right);
        } else {
            int piv = QuickSort.findPivot(items, left, right);
            int pivFinal = QuickSort.partition(items, left, right, piv);
            introHelp(items, depth - 1, left, pivFinal - 1);
            introHelp(items, depth - 1, pivFinal + 1, right);
          
        }
    }
}

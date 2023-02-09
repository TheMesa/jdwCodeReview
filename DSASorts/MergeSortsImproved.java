
/**
 * Improved MergeSort class.
 * @author Jacob West
 * @version 1.0
 */

public class MergeSortsImproved {

 
    /**
     * Calls the recursive merge sort, initializes temp array.
     * Temp array is half the size of the items array.
     * @param <T> Type of item
     * @param items Array of items to be sorted
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort1(T[] items) {
        T[] temp = (T[]) new Comparable[items.length / 2 + 1];

        mergeSort1(items, temp, 0, items.length - 1);
    }
    
    /**
     * Recursive merge sort function.
     * @param <T> Type of item
     * @param items Array to be sorted
     * @param temp Temporary array to store left subarray in
     * @param left Left half of subarray
     * @param right Right half of subarray
     */
    private static <T extends Comparable<T>> void mergeSort1(T[] items, 
          T[] temp, int left, int right) {
        if (left >= right) {
            return; // Region has one record
        }

        int mid = (left + right) / 2; // Select midpoint

        mergeSort1(items, temp, left, mid); // Mergesort first half
        mergeSort1(items, temp, mid + 1, right); // Mergesort second half

        merge1(items, temp, left, mid, right);

    }
    /**
     * Merging function.
     * @param <T> Type of item
     * @param items Array of items to be sorted
     * @param temp Temporary array
     * @param left Left half of subarray
     * @param mid Middle of subarray
     * @param right Right half of subarray
     */
    
    private static <T extends Comparable<T>> void 
        merge1(T[] items, T[] temp, int left, int mid,
          int right) {

        for (int i = left; i <= mid; i++) {
            temp[i - left] = items[i]; // Copy subarray to temp
        }

        int i1 = 0;
        int i2 = mid + 1;
        for (int curr = left; curr <= right; curr++) {
            if (i1 + left == mid + 1) { // Left subarray exhausted
                items[curr] = items[i2++];
            } else if (i2 > right) { // Right subarray exhausted
                items[curr] = temp[i1++];
            } else if (
                    temp[i1].compareTo(items[i2]) <= 0) { // Get smaller value
                items[curr] = temp[i1++];
            } else {
                items[curr] = items[i2++];
            }
        }
    }
    /**
     * Calls the recursive merge sort, initializes temp array.
     * Temp array is half the size of the items array.
     * @param <T> Type of item
     * @param items Array of items to be sorted
     */
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort2(T[] items) {
        T[] temp = (T[]) new Comparable[items.length / 2 + 1];

        mergeSort2(items, temp, 0, items.length - 1);
    }
  
    /**
     * Recursive merge sort function. Uses insertion sort if the subarray
     * is below a certain size.
     * @param <T> Type of item
     * @param items Array to be sorted
     * @param temp Temporary array to store left subarray in
     * @param left Left half of subarray
     * @param right Right half of subarray
     */
    private static <T extends Comparable<T>> void mergeSort2(T[] items, 
          T[] temp, int left, int right) {
        if (left >= right) {
            return; // Region has one record
        }

        int mid = (left + right) / 2; // Select midpoint
        int leftSize = mid - left;
        int rightSize = right - mid;
        int threshold = 85;
        if (leftSize <= threshold) {
            BasicSorts.insertionSort(items, left, mid);
        } else {
            mergeSort2(items, temp, left, mid); // Mergesort first half
        }
        if (rightSize <= threshold) {
            BasicSorts.insertionSort(
                    items, mid + 1, right);
        } else {
            mergeSort2(items, temp, mid + 1, right); // Mergesort second half
        }
        merge1(items, temp, left, mid, right);
    }

    /**
     * Sorts a subarray from index start to index end using merge2.
     * @param <T> Type of item
     * @param items Array of items to be sorted
     * @param start Start subarray of items
     * @param end End of subarray of items
     */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort2(T[] items, 
        int start, int end) {
        T[] temp = (T[]) new Comparable[items.length / 2 + 1];

        mergeSort2(items, temp, start, end);
    }

}

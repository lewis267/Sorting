import java.security.Key;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by root on 3/4/17.
 *
 * Quicksort implementation.
 */
public class Quick implements Sort {
    private static int CUTOFF = 10;

    public <Key extends Comparable<Key>> void sort(Key[] a) {
        //shuffle for performance garuntee
        shuffle(a);
        sort(a, 0, a.length - 1);
    }
    public void sort(Object[] a, Comparator c) {
        shuffle(a);
        sort(a, 0, a.length -1, c);
    }

    public static <Key extends Comparable<Key>> Key select(Key[] a, int k) {
        shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo)
        {
            int j = partition(a, lo, hi);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else            return a[k];
        }
        return a[k];
    }
    public static Object select(Key[] a, Comparator c, int k) {
        shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi, c);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else            return a[k];
        }
        return a[k];
    }

    private static <Key extends Comparable<Key>> int partition(Key[] a, int lo, int hi) {
        int i = lo, j = hi;
        while (true)
        {
            //find item on the left to swap
            while (Sort.less(a[++i], a[lo]))
                if (i == hi) break;

            //find item on right to swap
            while (Sort.less(a[lo], a[--j]))
                if (j == lo) break;

            //check if pointers cross
            if (i >= j) break;
            //swap
            Sort.exch(a, i, j);
        }

        //swap with partitioning item
        Sort.exch(a, lo, j);

        //return index of item now known to be in place
        return j;
    }
    private static int partition(Object[] a, int lo, int hi, Comparator c) {
        int i = lo, j = hi;
        while (true)
        {
            //find item on the left to swap
            while (Sort.less(c, a[++i], a[lo]))
                if (i == hi) break;

            //find item on right to swap
            while (Sort.less(c, a[lo], a[--j]))
                if (j == lo) break;

            //check if pointers cross
            if (i >= j) break;
            //swap
            Sort.exch(a, i, j);
        }

        //swap with partitioning item
        Sort.exch(a, lo, j);

        //return index of item now known to be in place
        return j;
    }

    private static <Key extends Comparable<Key>> void sort(Key[] a, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }

        int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi);
        Sort.exch(a, lo, m);

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    private static void sort(Object[] a, int lo, int hi, Comparator c) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi, c);
            return;
        }

        int m = medianOf3(a, lo, lo + (hi - lo) / 2, hi, c);
        Sort.exch(a, lo, m);

        int j = partition(a, lo, hi, c);
        sort(a, lo, j-1, c);
        sort(a, j+1, hi, c);
    }

    private static <Key extends Comparable<Key>> int medianOf3(Key[] a, int i, int j, int k) {
        return Sort.less(a[i], a[k]) ? (Sort.less(a[i], a[j]) ? (Sort.less(a[j], a[k]) ? j : k) : i) : (Sort.less(a[i], a[j]) ? i : (Sort.less(a[j], a[k]) ? k : j));
    }
    private static int medianOf3(Object[] a, int i, int j, int k, Comparator c) {
        return Sort.less(c, a[i], a[k]) ? (Sort.less(c, a[i], a[j]) ? (Sort.less(c, a[j], a[k]) ? j : k) : i) : (Sort.less(c, a[i], a[j]) ? i : (Sort.less(c, a[j], a[k]) ? k : j));
    }

    private static <Key extends Comparable<Key>> void shuffle(Key[] a) {
        Random random = new Random();
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + random.nextInt(N-i);     // between i and N-1
            Key temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
    private static void shuffle(Object[] a) {
        Random random = new Random();
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int r = i + random.nextInt(N-i);     // between i and N-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }
}

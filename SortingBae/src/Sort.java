import java.util.Comparator;

/**
 * Created by root on 3/4/17.
 *
 * An interface for sorting algorithms.
 */
public interface Sort {
    int CUTOFF = 7;

    //Using Comparable
    <Key extends Comparable<Key>> void sort(Key[] a);
    static <Key extends Comparable<Key>> boolean less(Key v, Key w) {
        return v.compareTo(w) < 0;
    }
    static <Key extends Comparable<Key>> void exch(Key[] a, int i, int j) {
        Key swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    static <Key extends Comparable<Key>> boolean isSorted(Key[] a, int lo, int hi) {
        assert lo >= 0 && lo <= hi && hi <= a.length;

        for (int i = lo + 1; i < hi; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    static <Key extends Comparable<Key>> boolean isSorted(Key[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    //Using a Comparator
    void sort(Object[] a, Comparator comparator);
    static boolean less(Comparator c, Object v, Object w) {
        return c.compare(v, w) < 0;
    }
    static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    static boolean isSorted(Object[] a, int lo, int hi, Comparator c) {
        assert lo >= 0 && lo <= hi && hi <= a.length;

        for (int i = lo + 1; i < hi; i++) {
            if (less(c, a[i], a[i-1])) return false;
        }
        return true;
    }
    static boolean isSorted(Object[] a, Comparator c) {
        for (int i = 1; i < a.length; i++) {
            if (less(c, a[i], a[i-1])) return false;
        }
        return true;
    }
}

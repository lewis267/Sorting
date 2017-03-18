import java.util.Comparator;

/**
 * Created by root on 3/4/17.
 *
 * Mergesort implementation.
 */
public class Merge implements Sort {
    private static int CUTOFF = 7;

    private static Comparable[] aux;
    private static Object[] auxO;

    public <Key extends Comparable<Key>> void sort(Key[] a) {
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    public void sort(Object[] a, Comparator c) {
        auxO = new Object[a.length];
        sort(a, auxO, 0, a.length - 1, c);
    }

    private static <Key extends Comparable<Key>> void sort(Key[] a, Key[] aux, int lo, int hi) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (!Sort.less(a[mid+1], a[mid])) return;

        sort (aux, a, lo, mid);
        sort (aux, a, mid+1, hi);
        merge(aux, a, lo, mid, hi);
    }
    private static void sort(Object[] a, Object[] aux, int lo, int hi, Comparator c) {
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(a, lo, hi, c);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (!Sort.less(c, a[mid+1], a[mid])) return;

        sort (aux, a, lo, mid, c);
        sort (aux, a, mid+1, hi, c);
        merge(aux, a, lo, mid, hi, c);
    }

    private static <Key extends Comparable<Key>> void merge(Key[] a, Key[] aux, int lo, int mid, int hi) {
        assert Sort.isSorted(a, lo, mid);
        assert Sort.isSorted(a, mid+1, hi);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                   aux[k] = a[j++];
            else if (j > mid)                   aux[k] = a[i++];
            else if (Sort.less(aux[j], aux[i])) aux[k] = a[j++];
            else                                aux[k] = a[i++];
        }

        assert Sort.isSorted(a, lo, hi);
    }
    private static void merge(Object[] a, Object[] aux, int lo, int mid, int hi, Comparator c) {
        assert Sort.isSorted(a, lo, mid, c);
        assert Sort.isSorted(a, mid+1, hi, c);

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                   aux[k] = a[j++];
            else if (j > mid)                   aux[k] = a[i++];
            else if (Sort.less(c, aux[j], aux[i])) aux[k] = a[j++];
            else                                aux[k] = a[i++];
        }

        assert Sort.isSorted(a, lo, hi, c);
    }
}

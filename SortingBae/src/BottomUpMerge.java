import java.util.Comparator;

/**
 * Created by root on 3/4/17.
 *
 * Bottom-up Mergesort implementation.
 */
public class BottomUpMerge implements Sort {
    private static Comparable[] aux;
    private static Object[] auxO;

    public <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz+sz)
            for (int lo = 0; lo < N-sz; lo += sz+sz)
                merge(aux, a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
    }
    public void sort(Object[] a, Comparator c) {
        int N = a.length;
        auxO = new Object[N];
        for (int sz = 1; sz < N; sz = sz+sz)
            for (int lo = 0; lo < N-sz; lo += sz+sz)
                merge(auxO, a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1), c);
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

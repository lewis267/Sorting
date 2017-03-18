import java.util.Comparator;

/**
 * Created by root on 3/4/17.
 *
 * Shellsort implementation.
 */
public class Shell implements Sort {
    //enum type { Xtimes3plus1, Sedgewick }

    public <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;

        int h = 1;
        while (h < N/3) h = 3*h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && Sort.less(a[j], a[j-h]); j -= h) {
                    Sort.exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }
    public void sort(Object[] a, Comparator c) {
        int N = a.length;

        int h = 1;
        while (h < N/3) h = 3*h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && Sort.less(c, a[j], a[j-h]); j -= h) {
                    Sort.exch(a, j, j-h);
                }
            }
            h = h/3;
        }
    }
}

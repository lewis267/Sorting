import java.util.Comparator;

/**
 * Created by root on 3/4/17.
 *
 * Selection-sort implementation.
 */
public class Selection implements Sort {

    public <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (Sort.less(a[j], a[min])) min = j;
            }
            Sort.exch(a, i, min);
        }
    }
    public void sort(Object[] a, Comparator c) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if (Sort.less(c, a[j], a[min])) min = j;
            }
            Sort.exch(a, i, min);
        }
    }
}

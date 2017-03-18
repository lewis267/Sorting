import java.util.Comparator;

/**
 * Created by root on 3/4/17.
 *
 * Insertion-sort implementaton.
 */
public class Insertion implements Sort {

    public <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (Sort.less(a[j], a[j-1])) Sort.exch(a, j, j-1);
                else break;
            }
        }
    }
    public void sort(Object[] a, Comparator comparator) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (Sort.less(comparator, a[j], a[j-1])) Sort.exch(a, j, j-1);
                else break;
            }
        }
    }

    static <Key extends Comparable<Key>> void sort(Key[] a, int lo, int hi) {
        assert lo >= 0 && lo <= hi && hi <= a.length;

        for (int i = lo; i < hi; i++) {
            for (int j = i; j > 0; j--) {
                if (Sort.less(a[j], a[j-1])) Sort.exch(a, j, j-1);
                else break;
            }
        }
    }
    static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        assert lo >= 0 && lo <= hi && hi <= a.length;

        for (int i = lo; i < hi; i++) {
            for (int j = i; j > 0; j--) {
                if (Sort.less(comparator, a[j], a[j-1])) Sort.exch(a, j, j-1);
                else break;
            }
        }
    }
}

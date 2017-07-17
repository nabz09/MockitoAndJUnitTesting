import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by nab_m on 04/07/2017.
 */
public class ArraysTest {
    @Test(expected=NullPointerException.class)
    public void testArraysNullPointer() {
        int[] a = null;
        Arrays.sort(a);
    }

    @Test
    public void testSortedCorrectly() {
        int[] a = {2,5,1,7,3};
        int[] expected = {1,2,3,5,7};
        Arrays.sort(a);

        Assert.assertArrayEquals(expected, a);
    }

    @Test(timeout=5000)
    public void testPerformanceOfTest() {
        int[] a = {2,4,1,17,13};
        for(int i=0; i<100000; i++) {
            a[0]=i;
            Arrays.sort(a);
            System.out.println(a);
        }
    }
}

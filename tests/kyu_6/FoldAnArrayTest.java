package kyu_6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

public class FoldAnArrayTest {
    @Test
    public void basicTests() {
        int[] input = new int[] { 1, 2, 3, 4, 5 };
        int[] expected = new int[] { 6, 6, 3 };
        assertEquals(Arrays.toString(expected), Arrays.toString(FoldAnArray.foldArray(input, 1)));

        expected = new int[] { 9, 6 };
        assertEquals(Arrays.toString(expected), Arrays.toString(FoldAnArray.foldArray(input, 2)));

        expected = new int[] { 15 };
        assertEquals(Arrays.toString(expected), Arrays.toString(FoldAnArray.foldArray(input, 3)));

        input = new int[] { -9, 9, -8, 8, 66, 23 };
        expected = new int[] { 14, 75, 0 };
        assertEquals(Arrays.toString(expected), Arrays.toString(FoldAnArray.foldArray(input, 1)));
    }

    @Test
    public void extendedTests() {
        int[] input = new int[] { 1, 2, 3, 4, 5, 99, 88, 78, 74, 73 };
        assertEquals(Arrays.toString(new int[] { 427 }), Arrays.toString(FoldAnArray.foldArray(input, 5)));

        input = new int[] { -1, -2, -3, -4, -5, -99, -88, -78, -74, -73 };
        assertEquals(Arrays.toString(new int[] { -427 }), Arrays.toString(FoldAnArray.foldArray(input, 5)));

        input = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        assertEquals(Arrays.toString( new int[] { 0 }), Arrays.toString(FoldAnArray.foldArray(input, 10)));

        input = new int[] { 2 };
        assertEquals(Arrays.toString(input), Arrays.toString(FoldAnArray.foldArray(input, 1)));

        input = new int[] { 2 };
        assertEquals(Arrays.toString(input), Arrays.toString(FoldAnArray.foldArray(input, 20)));
    }

    interface FoldArrayInterface {
        int[] foldArray(int[] array, int runs);
    }

    @Test
    public void randomTests() {
        FoldArrayInterface myMethod = new FoldArrayInterface() {
            public int[] foldArray(int[] array, int runs) {
                int length = (int)Math.ceil(array.length/2.0);
                int[] foldedArray = new int[length];

                for(int i = 0; i < length; i++) {
                    if(i != array.length - 1 - i)
                        foldedArray[i] = array[i] + array[array.length - 1 - i];
                    else
                        foldedArray[i] = array[i];
                }
                if(runs == 1) return foldedArray;
                return foldArray(foldedArray, runs - 1);
            }
        };

        for(int r = 0; r < 20; r++) {
            int length = (int)(Math.random() * 199 + 1);
            int runs = (int)(Math.random() * 19 + 1);
            int[] input = new int[length];
            for(int i=0;i<length;i++)
                input[i] = (int)(Math.random() * 401 - 200);

            int[] expected = myMethod.foldArray(input, runs);
            assertEquals(Arrays.toString(expected), Arrays.toString(FoldAnArray.foldArray(input, runs)));
        }
    }
}
package kyu_6;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class HowMuchTest {
    private static int randint(Random rnd, int min, int max) {
        int randomNumber = rnd.nextInt(max - min) + min;
        return randomNumber;
    }

    public static String howmuch3141(int m, int n) {
        int i = Math.min(m,  n);
        int j = Math.max(m,  n);
        String res = "[";
        while (i <= j) {
            if ((i % 9 == 1) && (i %7 == 2)) {
                res += "[M: " + i + " B: " + (int)(i / 7) + " C: " + (int)(i / 9) + "]";
            }
            i++;
        }
        return res + "]";
    }

    //----------------

    @Test
    public void BasicTests() {
        System.out.println("****** Basic tests ******");
        assertEquals("[[M: 37 B: 5 C: 4][M: 100 B: 14 C: 11]]",
                HowMuch.howmuch(1, 100));
        assertEquals("[[M: 37 B: 5 C: 4][M: 100 B: 14 C: 11][M: 163 B: 23 C: 18]]",
                HowMuch.howmuch(0, 200));
        assertEquals("[[M: 1045 B: 149 C: 116]]",
                HowMuch.howmuch(1000, 1100));
        assertEquals("[[M: 9991 B: 1427 C: 1110]]",
                HowMuch.howmuch(10000, 9950));
        assertEquals("[[M: 1549 B: 221 C: 172]]",
                HowMuch.howmuch(1500, 1600));
        assertEquals("[]",
                HowMuch.howmuch(2950, 2950));
        assertEquals("[[M: 20008 B: 2858 C: 2223][M: 20071 B: 2867 C: 2230]]",
                HowMuch.howmuch(20000, 20100));
    }
    @Test
    public void RandomTests() {
        System.out.println("****** Random tests ******");
        Random rnd = new Random();
        for (int i = 0; i < 50; i++) {
            int m = HowMuchTest.randint(rnd, 60000, 60550);
            int n = HowMuchTest.randint(rnd, 60551, 60999);
            assertEquals(HowMuchTest.howmuch3141(m, n), HowMuch.howmuch(m, n));
        }
    }
}
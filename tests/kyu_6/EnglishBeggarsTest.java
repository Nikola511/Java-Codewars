package kyu_6;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class EnglishBeggarsTest {
    public int[] solution(int[] values, int n){
        if(n == 0) return new int[0];

        int[] res = new int[n];
        for(int i = 0, len = values.length; i < len; i++)
            res[i % n] += values[i];

        return res;
    }

    public int getRandomInt(int start, int end){
        return (int) (Math.random() * (end - start + 1)) + start;
    }

    public int[] getRandomList(){
        int len = getRandomInt(3, 40), randPower;
        int[] list = new int[len];

        for(int i = 0; i < len; i++){
            randPower = (int) Math.pow(10, getRandomInt(1, 3));
            list[i] = getRandomInt(1, randPower);
        }

        return list;
    }

    @Test
    public void tester() throws Exception {
        // not my fault, but grouping tests in Java using `describe` is completely messed up
        // https://www.codewars.com/docs/java-test-reference
        // if can group, then do group these into basic and random tests

        // Basic tests
        int[] test = {1, 2, 3, 4, 5};
        int[] a1 = {15}, a2 = {9, 6}, a3 = {5, 7, 3}, a4 = {1, 2, 3, 4, 5, 0}, a5 = {};
        assertArrayEquals(EnglishBeggars.beggars(test, 1), a1);
        assertArrayEquals(EnglishBeggars.beggars(test, 2), a2);
        assertArrayEquals(EnglishBeggars.beggars(test, 3), a3);
        assertArrayEquals(EnglishBeggars.beggars(test, 6), a4);
        assertArrayEquals(EnglishBeggars.beggars(test, 0), a5);

        // Random tests
        int[] v; int n;
        for(int i = 0; i < 40; i++){
            v = getRandomList();
            n = getRandomInt(1,20);

            String msg = "n = " + n + ", values = " + Arrays.toString(v);
            System.out.println(msg);
            assertArrayEquals("It should work for random inputs too, here " + msg, solution(v,n),
                    EnglishBeggars.beggars(v,n));
        }
    }
}
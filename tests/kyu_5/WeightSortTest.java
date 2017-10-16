package kyu_5;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class WeightSortTest {
    //----------------
    private static int randint(Random rnd, int min, int max) {
        int randomNumber = rnd.nextInt(max - min) + min;
        return randomNumber;
    }
    private static int weightStrNbSol(String strng) {
        String[] digits = strng.split("");
        int dsum = 0;
        for (String d : digits) {
            int dgt = Integer.parseInt(d);
            dsum += dgt;
        }
        return dsum;
    }

    private static String orderWeightSol(String strng) {
        String[] lstr = strng.split(" ");

        Comparator<String> cmp = new Comparator<String>() {
            public int compare(String o1, String o2) {
                int cp =  weightStrNbSol(o1) - weightStrNbSol(o2);
                if (cp == 0)
                    return o1.compareTo(o2);
                if (cp < 0) return -1; else return 1;
            }
        };

        Arrays.sort(lstr, cmp);
        String res = String.join(" ", lstr);
        return res;
    }
    private static String doEx() {
        String res = "";
        Random rnd = new Random();
        for (int i =  0; i < 20; i++) {
            int n = randint(rnd, 1, 500000);
            res += n + " ";
        }
        int n = randint(rnd, 1, 100);
        return res + n;
    }
    //----------------

    @Test
    public void BasicTests() {
        System.out.println("****** Basic Tests ******");
        assertEquals("2000 103 123 4444 99", WeightSort.orderWeight("103 123 4444 99 2000"));
        assertEquals("11 11 2000 10003 22 123 1234000 44444444 9999", WeightSort.orderWeight("2000 10003 1234000 44444444 9999 11 11 22 123"));
        assertEquals("", WeightSort.orderWeight(""));
        assertEquals("2000 10003 1234000 44444444 9999 123456789", WeightSort.orderWeight("10003 1234000 44444444 9999 2000 123456789"));
        String a = "3 16 9 38 95 1131268 49455 347464 59544965313 496636983114762 85246814996697";
        String r = "3 16 9 38 95 1131268 49455 347464 59544965313 496636983114762 85246814996697";
        assertEquals(r, WeightSort.orderWeight(a));
        a = "71899703 200 6 91 425 4 67407 7 96488 6 4 2 7 31064 9 7920 1 34608557 27 72 18 81";
        r = "1 2 200 4 4 6 6 7 7 18 27 72 81 9 91 425 31064 7920 67407 96488 34608557 71899703";
        assertEquals(r, WeightSort.orderWeight(a));
        a = "387087 176 351832 100 430372 8 58052 54 175432 120 269974 147 309754 91 404858 67 271476 164 295747 111 40";
        r = "100 111 120 40 8 54 91 164 147 67 176 430372 58052 175432 351832 271476 309754 404858 387087 295747 269974";
        assertEquals(r, WeightSort.orderWeight(a));
    }
    @Test
    public void RandomTests() {
        System.out.println("****** Random tests ******");
        for (int i = 0; i < 50; i++) {
            String a = WeightSortTest.doEx();
            //System.out.println("Order Weight " + a);
            assertEquals(orderWeightSol(a), WeightSort.orderWeight(a));
        }
    }

}
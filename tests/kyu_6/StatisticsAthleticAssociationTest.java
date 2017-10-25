package kyu_6;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Random;
import org.junit.Test;

public class StatisticsAthleticAssociationTest {
    //---------------------------------------------
    private static int time2snd13241(String s) {
        String[] a = s.split("\\|");
        Object[] arr = Arrays.stream(a).map(x -> Integer.parseInt(x)).toArray();
        return 3600 * (int)arr[0] + 60 * (int)arr[1] + (int)arr[2];
    }
    private static String snd2time13241(int n) {
        int h = n / 3600;
        int re = n % 3600;
        int mn = re / 60;
        int s = re % 60;
        return String.format("%02d|%02d|%02d", h, mn, s);
    }
    private static String stat13241(String strg) {
        if (strg == "") return "";
        String[] a = strg.split("\\,\\s*");
        Object[] r = Arrays.stream(a).map(x -> time2snd13241(x)).toArray();
        Arrays.sort(r);
        int lg = r.length;
        int avg = 0;
        for (Object i : r)
            avg += (int)i;
        avg = (int)(avg / lg);
        int rge = (int)r[lg - 1] - (int)r[0];
        int md = (int)(((int)(r[ (int)((lg - 1) / 2) ]) + (int)(r[ (int)(lg / 2) ]))  / 2.0);
        return String.format("Range: %s Average: %s Median: %s", snd2time13241(rge), snd2time13241(avg), snd2time13241(md));
    }
    private static String comb(Random rnd) {
        String a = "01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17";
        String b = "02|15|59, 2|47|16, 02|17|20, 2|32|34, 2|17|17, 2|22|00, 2|31|41";
        String c = "02|15|59, 2|47|16, 02|17|20, 2|32|34, 2|32|34, 2|17|17";
        String d = "00|15|59, 00|16|16, 00|17|20, 00|22|34, 00|19|34, 00|15|17";
        String e = "11|15|59, 10|16|16, 12|17|20, 9|22|34, 13|19|34, 11|15|17, 11|22|00, 10|26|37, 12|17|48, 9|16|30, 12|20|14, 11|25|11";
        String f = "1|15|59, 1|16|16, 1|17|20, 1|22|34, 1|19|34, 1|15|17, 1|22|00, 1|26|37, 1|17|48, 1|16|30, 1|20|14, 1|25|11";

        String k = a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f;
        String[] v = k.split("\\,\\s*");
        int l = v.length;
        String res = "";
        int n = Math.round(rnd.nextInt(15) + 1);
        for (int i = 0; i < n; i++) {
            int rr = Math.round(rnd.nextInt(l-2) + 1);
            res += v[rr];
            if (i < n - 1) res += ", ";
        }
        return res;
    }
    //---------------------------------------------
    @Test
    public void BasicTests() {
        assertEquals("Range: 01|01|18 Average: 01|38|05 Median: 01|32|34",
                StatisticsAthleticAssociation.stat("01|15|59, 1|47|16, 01|17|20, 1|32|34, 2|17|17"));
        assertEquals("Range: 00|31|17 Average: 02|26|18 Median: 02|22|00",
                StatisticsAthleticAssociation.stat("02|15|59, 2|47|16, 02|17|20, 2|32|34, 2|17|17, 2|22|00, 2|31|41"));
        assertEquals("Range: 00|31|17 Average: 02|27|10 Median: 02|24|57",
                StatisticsAthleticAssociation.stat("02|15|59, 2|47|16, 02|17|20, 2|32|34, 2|32|34, 2|17|17"));
        assertEquals("Range: 00|07|34 Average: 00|17|47 Median: 00|16|48",
                StatisticsAthleticAssociation.stat("0|15|59, 0|16|16, 0|17|20, 0|22|34, 0|19|34, 0|15|0"));
        assertEquals("Range: 04|03|04 Average: 11|14|36 Median: 11|18|59",
                StatisticsAthleticAssociation.stat("11|15|59, 10|16|16, 12|17|20, 9|22|34, 13|19|34, 11|15|17, 11|22|00, 10|26|37, 12|17|48, 9|16|30, 12|20|14, 11|25|11"));
        assertEquals("Range: 00|11|20 Average: 01|19|36 Median: 01|18|41",
                StatisticsAthleticAssociation.stat("1|15|59, 1|16|16, 1|17|20, 1|22|34, 1|19|34, 1|15|17, 1|22|00, 1|26|37, 1|17|48, 1|16|30, 1|20|14, 1|25|11"));
    }

    @Test
    public void RandomTests() {
        System.out.println("****** Random tests ******");
        Random rnd = new Random();
        for (int i = 0; i < 20; i++) {
            String a = StatisticsAthleticAssociationTest.comb(rnd);
            //System.out.println(a);
            assertEquals(StatisticsAthleticAssociationTest.stat13241(a), StatisticsAthleticAssociation.stat(a));
        }
    }
}
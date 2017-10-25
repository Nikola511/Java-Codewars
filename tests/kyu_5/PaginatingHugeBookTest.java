package kyu_5;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.concurrent.ThreadLocalRandom;

public class PaginatingHugeBookTest {
    private static long sol(long pages) {
        long mul = 1;
        long cnt = 1;
        long total = 0;
        while (pages / mul >= 10) {
            total += 9 * mul * cnt;
            mul *= 10;
            cnt++;
        }
        total += (pages - mul + 1) * cnt;
        return total;
    }

    @Test
    public void testFour(){ assertEquals(4, PaginatingHugeBook.pageDigits(4)); }
    @Test
    public void testTwelve(){ assertEquals(15, PaginatingHugeBook.pageDigits(12)); }
    @Test
    public void testOneHundred(){ assertEquals(192, PaginatingHugeBook.pageDigits(100)); }
    @Test
    public void testHuge1(){ assertEquals(7088888888888888907L, PaginatingHugeBook.pageDigits(400000000000000000L)); }

    @Test
    public void testHuge2(){ assertEquals(5428894021529291739L, PaginatingHugeBook.pageDigits(307778062924466824L)); }

    @Test
    public void testRandom(){
        for(int i=0; i<100; i++) {
            long n = ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE/20);
            System.out.println("Testing for: "+String.valueOf(n));
            assertEquals(sol(n), PaginatingHugeBook.pageDigits(n));
        }
    }
}
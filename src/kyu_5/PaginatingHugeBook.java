package kyu_5;

import java.util.Arrays;
import java.util.stream.*;

public class PaginatingHugeBook {
    public static long pageDigits(long pages) {
        if(pages < 10) return pages;

        int[] arr = Arrays.stream(String.valueOf(pages).split("")).mapToInt(Integer::parseInt).toArray();
        int len = arr.length;

        long downDigits = 0l;
        for(int i = 0; i < len-1; ++i) {
            long pow = 1l;
            for(int j = 1; j <= i; ++j) pow *= 10;
            downDigits += pow * 9 * (i + 1);
        }

        --arr[0];
        long upCount = 1l + Long.parseLong(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining("")));

        return downDigits + upCount * len;
    }
}

/* Johnny is working as an intern for a publishing company, and was tasked with cleaning up old code. He is working on a program that determines how many digits are in all of the page numbers in a book with pages from 1 to n (inclusive).

For example, a book with 4 pages has 4 digits (one for each page) while a 12-page book has 15 (9 for 1-9, plus 2 each for 10, 11, 12).

Johnny's boss, looking to futureproof, demanded that the new program be able to handle books up to 400,000,000,000,000,000 pages.

Since there are 41 testcases of huge numbers, it means that you have less than 0.25 seconds to return a result for each book.

Hint: Johnny doesn't trust the IEEE, especially their specification number 754, for good reason. */
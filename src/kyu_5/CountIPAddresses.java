package kyu_5;

import java.util.*;

public class CountIPAddresses {
    public static long ipsBetween(String start, String end) {
        final int A = 16777216, B = 65536, C = 256;
        long[] arr1 = Arrays.stream(start.split("\\.")).mapToLong(Long::parseLong).toArray(),
               arr2 = Arrays.stream(end.split("\\."))  .mapToLong(Long::parseLong).toArray();
        return arr2[0] * A + arr2[1] * B + arr2[2] * C + arr2[3] -
               (arr1[0] * A + arr1[1] * B + arr1[2] * C + arr1[3]);
    }
}

/* Write a function that accepts a starting and ending IPv4 address, and returns the number of IP addresses from start
to end, excluding the end IP address.
All input to the ipsBetween function will be valid IPv4 addresses in the form of strings. The ending address will be at
least one address higher than the starting address.

Examples:
ipsBetween("10.0.0.0", "10.0.0.50") => returns 50
ipsBetween("10.0.0.0", "10.0.1.0") => returns 256
ipsBetween("20.0.0.10", "20.0.1.0") => returns 246
* */
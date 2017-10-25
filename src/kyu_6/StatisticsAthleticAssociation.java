package kyu_6;

import java.util.Arrays;

class StatisticsAthleticAssociation {
    private static String toString(int n) {
        int h = n / 3600, m = n % 3600 / 60, s = n % 3600 % 60;
        return String.format("%02d|%02d|%02d", h, m, s);
    }

    static String stat(String str) {
        String[] arr1 = str.split(", ");
        int len = arr1.length;
        int[] secs = new int[arr1.length];
        for(int i = 0; i < arr1.length; ++i) {
            String[] arr2 = arr1[i].split("\\|");
            secs[i] = Integer.parseInt(arr2[0]) * 3600 +
                    Integer.parseInt(arr2[1]) * 60 +
                    Integer.parseInt(arr2[2]);
        }
        Arrays.sort(secs);
        int rng = secs[len-1] - secs[0],
                avg = (int)Arrays.stream(secs).average().getAsDouble(),
                med = len % 2 > 0 ? secs[len/2] : (secs[len/2-1] + secs[len/2])/2;

        StringBuilder sb = new StringBuilder();
        sb.append("Range: ").append(toString(rng))
                .append(" Average: ").append(toString(avg))
                .append(" Median: ").append(toString(med));

        return str.equals("") ? str : sb.toString();
    }
}

/* You are the "computer expert" of a local Athletic Association (C.A.A.). Many teams of runners come to compete. Each time you get a string of all race results of every team who has run. For example here is a string showing the individual results of a team of 5:

"01|15|59, 1|47|6, 01|17|20, 1|32|34, 2|3|17"

Each part of the string is of the form: h|m|s where h, m, s are positive or null integer (represented as strings) with one or two digits. There are no traps in this format.

To compare the results of the teams you are asked for giving three statistics; range, average and median.

Range : difference between the lowest and highest values. In {4, 6, 9, 3, 7} the lowest value is 3, and the highest is 9, so the range is 9 − 3 = 6.

Mean or Average : To calculate mean, add together all of the numbers in a set and then divide the sum by the total count of numbers.

Median : In statistics, the median is the number separating the higher half of a data sample from the lower half. The median of a finite list of numbers can be found by arranging all the observations from lowest value to highest value and picking the middle one (e.g., the median of {3, 3, 5, 9, 11} is 5) when there is an odd number of observations. If there is an even number of observations, then there is no single middle value; the median is then defined to be the mean of the two middle values (the median of {3, 5, 6, 9} is (5 + 6) / 2 = 5.5).

Your task is to return a string giving these 3 values. For the example given above, the string result will be

"Range: 00|47|18 Average: 01|35|15 Median: 01|32|34"

of the form:

"Range: hh|mm|ss Average: hh|mm|ss Median: hh|mm|ss"

where hh, mm, ss are integers (represented by strings) with each 2 digits.

Remarks:

if a result in seconds is ab.xy... it will be given truncated as ab.
if the given string is "" you will return ""
 */
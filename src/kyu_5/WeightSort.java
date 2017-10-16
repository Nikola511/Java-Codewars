package kyu_5;

import java.util.Arrays;
class WeightSort {
    static String orderWeight(final String str) {
        if(str.equals("")) return str;
        long[] arr = Arrays.stream(str.split(" ")).mapToLong(Long::parseLong).toArray();
        long[] sorted = Arrays.stream(arr).boxed().sorted((a, b) -> {
            String s0 = String.valueOf(a),
                    s1 = String.valueOf(b);
            long sum0 = Arrays.stream(s0.split("")).mapToLong(Long::parseLong).sum(),
                    sum1 = Arrays.stream(s1.split("")).mapToLong(Long::parseLong).sum();
            if(sum0 == sum1) return s0.compareTo(s1);
            return (int)(sum0 - sum1);
        })
                .mapToLong(i -> i).toArray();
        return String.join(" ", Arrays.stream(sorted).mapToObj(String::valueOf).toArray(String[]::new));
    }
}

/* Best solution:
import java.util.*;
public class WeightSort {
  public static String orderWeight(String string) {
    String[] split = string.split(" ");
    Arrays.sort(split, new Comparator<String>() {
      public int compare(String a, String b) {
        int aWeight = a.chars().map(c -> Character.getNumericValue(c)).sum();
        int bWeight = b.chars().map(c -> Character.getNumericValue(c)).sum();
        return aWeight - bWeight != 0 ? aWeight - bWeight : a.compareTo(b);
      }
    });
    return String.join(" ", split);
  }
}
*/

/* My friend John and I are members of the "Fat to Fit Club (FFC)". John is worried because each month a list with the weights of members is published and each month he is the last on the list which means he is the heaviest.

I am the one who establishes the list so I told him: "Don't worry any more, I will modify the order of the list". It was decided to attribute a "weight" to numbers. The weight of a number will be from now on the sum of its digits.

For example 99 will have "weight" 18, 100 will have "weight" 1 so in the list 100 will come before 99. Given a string with the weights of FFC members in normal order can you give this string ordered by "weights" of these numbers?

Example:

"56 65 74 100 99 68 86 180 90" ordered by numbers weights becomes: "100 180 90 56 65 74 68 86 99"

When two numbers have the same "weight", let us class them as if they were strings and not numbers: 100 is before 180 because its "weight" (1) is less than the one of 180 (9) and 180 is before 90 since, having the same "weight" (9) it comes before as a string.

All numbers in the list are positive numbers and the list can be empty.
*/

package kyu_6;

public class EnglishBeggars {
    public static int[] beggars(int[] values, int n) {
        if(values.length == 0 || n < 1) return new int[] {};
        int[] res = new int[n];
        for(int i = 0; i < values.length; ++i) {
            int begarIndex = i % n;
            res[begarIndex] += values[i];
        }
        return res;
    }
}

/* Born a misinterpretation of this kata, your task here is pretty simple: given an array of values and an amount of
beggars, you are supposed to return an array with the sum of what each beggar brings home, assuming they all take
regular turns, from the first to the last.
For, for example: [1,2,3,4,5] for 2 beggars will return a result of [9,6], as the first one takes [1,3,5], the second
collects [2,4].
The same array with 3 beggars would have in turn have produced a better out come for the second beggar: [5,7,3], as
they will respectively take [1,4], [2,5] and [3].
Also note that not all beggars have to take the same amount of "offers", meaning that the length of the array is not
necessarily a multiple of n; length can be even shorter, in which case the last beggers will of course take nothing (0).
Note: n case you don't get why this kata is about English beggars, then you are not familiar on how religiously queues
are taken in the kingdom ;)
*/
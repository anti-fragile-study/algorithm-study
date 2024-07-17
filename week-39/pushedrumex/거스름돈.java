import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[] count = new int[n+1];
        count[0] = 1;
        for (int m: money) {
            for (int value=0;value<n;value++) {
                if (value+m > n) break;
                count[value+m] += count[value];
            }
        }

        return count[n] % 1_000_000_007;
    }
}
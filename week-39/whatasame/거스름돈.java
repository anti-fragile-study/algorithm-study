// 백트래킹으로 모든 경우의 수를 따진다 -> O(k^(n/min_money)) k is money.len -> 불가능

import java.util.*;

class Solution {

    static int[] maxCount;

    public int solution(int n, int[] money) {
        // dp[i][j] = i번째까지 모든 종류의 동전을 이용하여 거슬러 줄 수 있는 경우의 수
        int[][] dp = new int[money.length + 1][n + 1];

        for (int idx = 1; idx <= money.length; idx++) {
            for (int sum = 0; sum <= n; sum++) {
                if (sum == 0) {
                    dp[idx][sum] = 1;
                } else if (sum - money[idx - 1] >= 0) {
                    // idx번째 동전을 사용하는 경우 남은 돈은 dp[idx][sum - money[idx - 1]]
                    dp[idx][sum] = dp[idx - 1][sum] + dp[idx][sum - money[idx - 1]];
                    dp[idx][sum] %= 1_007_000_007;
                } else {
                    dp[idx][sum] = dp[idx - 1][sum];
                }

            }
        }

        return dp[money.length][n];
    }
}

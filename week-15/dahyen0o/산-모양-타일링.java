import java.util.*;

class Solution {
    
    private static final long DIV = 10_007;
    
    public int solution(final int N, final int[] tops) {
        // dp[][0]: 왼쪽 누운 마름모, 세운 마름모, no 마름모
        // dp[][1]: 오른쪽 누운 마름모 -> 다음 dp에 영향
        final long[][] dp = new long[N][2];
        dp[0][0] = (tops[0] == 1) ? 3 : 2;
        dp[0][1] = 1;
        
        for(int i = 1; i < dp.length; i++) {
            if(tops[i] == 1) {
                dp[i][0] += dp[i - 1][0]; // 왼쪽 누운 마름모
                dp[i][0] += dp[i - 1][0] + dp[i - 1][1]; // 세운 마름모
                dp[i][0] += dp[i - 1][0] + dp[i - 1][1]; // no 마름모
            } else {
                dp[i][0] += dp[i - 1][0];
                dp[i][0] += dp[i - 1][0] + dp[i - 1][1];
            }
            
            dp[i][1] += dp[i - 1][0] + dp[i - 1][1];
            
            dp[i][0] %= DIV;
            dp[i][1] %= DIV;
        }
        
        return (int)((dp[N - 1][0] + dp[N - 1][1]) % DIV);
    }
}

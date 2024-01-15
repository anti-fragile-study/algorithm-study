import java.util.*;

class Solution {
    public int solution(final int N, final int[] tops) {
        // dp[n][0] = 해당 n에 마름모 타일이 없음
        // dp[n][1] = 해당 n에 마름모 타일이 있음
        final int[][] dp = new int[N + 1][2];
        
        for(int bottom = 0; bottom <= N; bottom++) {
            if(bottom % 2 > 0 && tops[bottom / 2] == 1) { 
                // tops 존재
                dp[bottom][1] = dp[bottom][b]
            }
        }
        int answer = 0;
        return answer;
    }
}

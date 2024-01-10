class Solution {
    private static final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // n = 0일 경우, 초기화
        int newPattern = dp[0]; // 마지막이 정삼각형으로 끝나는 경우, 새로운 패턴이 생성된다.
        for (int i = 1; i <= n; i++) {
            // 이전 패턴에서 마름모, 두 정삼각형으로 이뤄진 마름모 두 타일을 붙인다. 그리고 새로운 패턴을 더한다.
            dp[i] = (dp[i - 1] * 2 + newPattern) % MOD;
            // 정삼각형으로 이뤄진 두 마름모 타일을 붙이는 경우, 정삼각형으로 끝난다.
            newPattern = (newPattern + dp[i - 1]) % MOD;
			
            // tops가 있는 경우, 위로 향하는 마름모가 생기는 새로운 패턴이 생길 수 있는데, 이 경우는, tops의 아래가 정삼각형이어야 한다.
            // 이 경우는 두 정삼각형으로 이뤄진 마름모 타일을 붙이는 경우만 생성될 수 있다. 이 경우만 더 추가해준다.
            if (tops[i - 1] == 1) { 
                dp[i] = (dp[i] + dp[i - 1]) % MOD;
                // 이 경우 두 정삼각형으로 이뤄진 마름모 타일을 가지는 패턴이 증가해야한다.
            	newPattern = (newPattern + dp[i - 1]) % MOD;
            }
        }
        return dp[n];
    }
}

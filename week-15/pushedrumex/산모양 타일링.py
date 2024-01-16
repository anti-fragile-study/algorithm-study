C = 10007
def solution(n, tops):
    m = n * 3 + 1
    dp = [0] * m
    dp[0], dp[1] = 1, 2
    
    for i in range(2, m):
        if i % 3 == 2:
            if tops[(i+1) // 3 - 1] == 1:
                dp[i] = (dp[i-1] + dp[i-2]) % C
            else:
                dp[i] = dp[i-1]
        elif i % 3 == 0:
            dp[i] = (dp[i-1] + dp[i-3]) % C
        elif i % 3 == 1:
            dp[i] = (dp[i-1] + dp[i-2]) % C

    return dp[-1]
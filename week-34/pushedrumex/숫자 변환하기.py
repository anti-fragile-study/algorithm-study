INF = 10**9
def solution(x, y, n):
    dp = [INF] * (y+1)
    dp[y] = 0
    for i in range(y, x-1, -1):
        if i > n:
            dp[i-n] = min(dp[i-n], dp[i]+1)
        if i % 2 == 0:
            dp[i // 2] = min(dp[i // 2], dp[i]+1)
        if i % 3 == 0:
            dp[i // 3] = min(dp[i // 3], dp[i]+1)

    return -1 if dp[x] == INF else dp[x]
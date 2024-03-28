INF = int(1e9)

N = int(input())
dp = [INF] * (5000+1)
dp[3], dp[5] = 1, 1

for i in range(6, N+1):
    if dp[i-3] != INF:
        dp[i] = dp[i-3] + 1
    if dp[i-5] != INF:
        dp[i] = min(dp[i], dp[i-5] + 1)

print(dp[N]) if dp[N] != INF else print(-1)
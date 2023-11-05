N = int(input())
schedule = [list(map(int, input().split())) for _ in range(N)]

dp = [0] * (N+2)
for i in range(N, 0, -1):
    t, p = schedule[i-1]
    if i + t - 1 <= N:
        dp[i] = max(dp[i + t] + p, dp[i])

print(dp[1])
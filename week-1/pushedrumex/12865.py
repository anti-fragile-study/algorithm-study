N, K = map(int, input().split())
goods = [list(map(int, input().split())) for _ in range(N)]

dp = [[0] * (K+1) for _ in range(N+1)]

for n in range(1, N+1):
    w, v = goods[n-1]
    
    for k in range(1, K+1):
        dp[n][k] = dp[n-1][k]
        if w <= k:
            dp[n][k] = max(dp[n][k], dp[n-1][k-w] + v)

print(dp[N][K])
N = int(input())

dp = ["CY"] * 1001

dp[1] = "SK"
dp[3] = "SK"

for i in range(4, N+1):
    if dp[i-3] == "CY" or dp[i-1] == "CY":
        dp[i] = "SK"

print(dp[N])
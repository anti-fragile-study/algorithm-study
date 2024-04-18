N = int(input())
nums = list(map(int, input().split()))
M = int(input())

dp = [""] * (M+1)

price = nums[0]
for m in range(price, M+1):
    dp[m] = "0" + dp[m-price]

# 숫자를 하나씩 사용
for n in range(N-1, 0, -1):
    price = nums[n]
    for m in range(price, M+1):
        n1, n2 = int(dp[m] if dp[m] != "" else 0), int(dp[m-price] + str(n))
        if n1 < n2:
            dp[m] = str(n2)

print(int(dp[-1]))

T = int(input())

for _ in range(T):
    N = int(input())
    coins = list(map(int, input().split()))
    M = int(input())

    dp = [0] * (M+1)
    dp[0] = 1

    # coin 종류 하나씩 사용
    for coin in coins:
        # coin 을 포함하여 만들수 있는 금액 + 1
        for m in range(coin, M+1):
            dp[m] += dp[m-coin]

    print(dp[M])
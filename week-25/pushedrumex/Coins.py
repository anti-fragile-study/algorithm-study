T = int(input())
for _ in range(T):
    N = int(input())
    coins = list(map(int, input().split()))
    M = int(input())

    # m 원을 만들 수 있는 경우의 수
    dp = [0]*(M+1)
    # coin 을 하나씩 사용
    for coin in coins:
        dp[coin] += 1
        for m in range(1, M+1):
            if coin > m:
                continue
            dp[m] += dp[m-coin]
        
    print(dp[M])
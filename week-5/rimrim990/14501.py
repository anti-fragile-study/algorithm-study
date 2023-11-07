# 백준 14501 퇴사
n = int(input())
data = []

for _ in range(n):
    _in = list(map(lambda x: int(x), input().split()))
    data.append(_in)

dp = [0 for _ in range(n+1)]

for today in range(n):
    time, pay = data[today]
    # 오늘 상담을 진행한다.
    if today + time <= n:
        dp[today + time] = max(dp[today] + pay, dp[today + time])
    # 오늘 아무것도 하지 않는다.
    dp[today+1] = max(dp[today], dp[today+1])

print(dp[n])
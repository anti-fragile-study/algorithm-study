# 5와 3을 사용 -> 23:4+1, 18:3+1, 11:1+2, 256:50+2
# 5만 사용 -> 15:3+0
# 3만 사용 -> 6:0:2
# 둘 다 불가능 -> 4

import sys

INF = 987654321

input = sys.stdin.readline
n = int(input())

def solution(num):
    dp = [INF for _ in range(5001)]

    # 5만 가능
    for idx in range(5, 5001, 5):
        dp[idx] = idx // 5

    # 3만 가능
    for idx in range(3, 5001, 3):
        dp[idx] = min(dp[idx], idx // 3)

    # 5, 3 둘 다 가능
    for idx in range(3, 5001):
        if dp[idx - 3] != INF:
            dp[idx] = min(dp[idx - 3] + 1, dp[idx])

    return dp[num] if dp[num] != INF else -1

print(solution(n))

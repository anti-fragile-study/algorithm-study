# N가지 동전으로 M원을 만드는 방법의 수를 반환

import sys

input = sys.stdin.readline

t = int(input())
cases = []
for _ in range(t):
    n = int(input())
    coins = (list(map(int, input().split())))
    m = int(input())

    cases.append((coins, m))

def solution(cases):
    return [run(*case) for case in cases]

def run(coins, m):
    # dp 배열 선언 (0 ~ m)
    dp = [0 for _ in range(m + 1)] # 0 ~ m

    # 점화식
    dp[0] = 1
    for coin in coins:
        for now in range(m + 1):
            if now < coin:
                continue

            dp[now] += dp[now - coin]

    return dp[m]


print("\n".join(map(str, solution(cases))))

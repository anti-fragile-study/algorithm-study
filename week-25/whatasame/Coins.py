# N가지 동전으로 금액 M을 만드는 모든 방법의 수를 반환

import sys

input = lambda:sys.stdin.readline().strip()

t = int(input())
cases = []
for _ in range(t):
    n = int(input())
    coins = list(map(int, input().split()))
    m = int(input())
    cases.append((n, coins, m))

def solution(t, cases):
    return [run(case) for case in cases]

def run(case):
    n, coins, m = case
    dp = [0 for _ in range(m + 1)] # 0 ~ m
    dp[0] = 1

    for coin in coins:
        for money in range(1, m + 1):
            if money < coin:
                continue
            dp[money] += dp[money - coin]

    return dp[m]

print("\n".join(map(str, solution(t, cases))))

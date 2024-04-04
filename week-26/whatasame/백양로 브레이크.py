# 공학관 -x> 종합관
# 출발지 - 도착지 최소 양방향 엣지 개수 반환

"""
4 4
1 2 1
2 3 1
3 4 1
1 4 0
1
4 1
-> 0
"""

import sys

input = lambda:sys.stdin.readline().strip()

n, m = map(int, input().split())
info = [tuple(map(int, input().split())) for _ in range(m)]
k = int(input())
questions = [tuple(map(int, input().split())) for _ in range(k)]

def solution(info, questions):
    # 그래프 초기화
    INF = 987654321
    dp = [[0 if r == c else INF for c in range(n + 1)] for r in range(n + 1)]
    for u, v, b in info:
        dp[u][v] = 0
        dp[v][u] = 0 if b else 1

    # floyd-warshall
    for node in range(1, n + 1):
        for start in range(1, n + 1):
            for end in range(1, n + 1):
                dp[start][end] = min(dp[start][end], dp[start][node] + dp[node][end])

    return [dp[s][e]for s, e in questions]

print("\n".join(map(str, solution(info, questions))))

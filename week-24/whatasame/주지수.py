# 주어진 직사각형 범위 내 총 사람 수를 반환

"""
4 5
1 2 3 4 5
6 7 8 9 1
2 3 4 5 6
7 8 9 1 2
1
2 2 3 3
-> 22
"""

import sys

input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]
k = int(input())
cases = [tuple(map(int, input().split())) for _ in range(k)]

def solution(board, cases):
    # 행, 열 누적합 배열 선언
    dp = [[0 for _ in range(m + 1)] for _ in range(n + 1)]

    # 누적합 초기화
    for r in range(1, n + 1):
        for c in range(1, m + 1):
            dp[r][c] = board[r - 1][c - 1] + dp[r - 1][c] + dp[r][c - 1] - dp[r - 1][c - 1]

    # 케이스 구하기
    return [dp[er][ec] - dp[sr - 1][ec] - dp[er][sc - 1] + dp[sr - 1][sc - 1] for sr, sc, er, ec in cases]

print("\n".join(map(str, solution(board, cases))))

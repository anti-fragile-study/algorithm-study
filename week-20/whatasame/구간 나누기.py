# M개의 구간의 합의 최댓값을 반환

# 연속된 수 = 배열 상으로 이어져있어야함 e.g. 3 1 2 4 => 3 1 2 (o), 3 2 4 (x)

import sys

input = sys.stdin.readline

n, m = map(int, input().split())
nums = [int(input()) for _ in range(n)]
INF = -987654321

def solution(nums):
    # i번째 원소를 포함한, 포함하지 않는 구간 j개의 최대합
    include, exclude = [[0] + [INF] * m for _ in range(n + 1)], [[0] + [INF] * m for _ in range(n + 1)]

    for i in range(1, n + 1):
        num = nums[i - 1]
        for j in range(1, min(m, (i + 1) // 2) + 1):
            include[i][j] = max(include[i - 1][j], exclude[i - 1][j - 1]) + num
            exclude[i][j] = max(include[i - 1][j], exclude[i - 1][j])

    return max(include[n][m], exclude[n][m])

print(solution(nums))

# M명의 입국 심사에 걸리는 최솟값을 반환

# 사람 M명, 줄 N개, T번째 줄의 심사 시간 Tk
# 다른 심사대로 이동 가능

"""
3 18
6
8
15
-> 54
"""

import sys

input = sys.stdin.readline

n, m = map(int, input().split())
costs = [int(input()) for _ in range(n)]

def solution(m, costs):
    left, right = 0, min(costs) * m

    while left <= right:
        time = (left + right) // 2

        available = sum(time // cost for cost in costs)

        if available >= m:
            right = time - 1
        else:
            left = time + 1

    return left

print(solution(m, costs))

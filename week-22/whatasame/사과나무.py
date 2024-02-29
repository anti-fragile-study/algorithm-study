# 초기 높이가 0인 나무 1 ~ N번

# 물을 뿌릴 때마다 1, 2 짜리를 반드시 써야함

"""
7
1 3 1 3 1 4 2
-> YES

2
5 1
-> YES
"""

import sys

input = sys.stdin.readline
n = int(input())
heights = list(map(int, input().split()))

def solution(heights):
    while heights:
        # stack 크기가 1이고 남아있는게 1, 2라면 NO
        if len(heights) == 1 and 1 <= heights[-1] < 3:
            return "NO"

        # 나무 한 개 pop
        height = heights.pop()
        result = height % 3 # 3 모듈러

        # 0 이면 pass
        if result == 0:
            continue

        # 1, 2 이면 merge
        if heights:
            heights[-1] += result
        else:
            heights.append(result)

    return "YES"

print(solution(heights))

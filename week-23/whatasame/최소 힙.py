# 자연수라면 push, 0이면 pop

"""
3
0
0
0
-> 0 0 0

6
9
5
0
8
0
0
-> 5 8 9
"""

import sys
from heapq import *

input = sys.stdin.readline

n = int(input())
numbers = [int(input()) for _ in range(n)]

def solution(numbers):
    min_heap, answer = [], []

    for num in numbers:
        if num:
            heappush(min_heap, num)
        elif not min_heap:
            answer.append(0)
        else:
            answer.append(heappop(min_heap))

    return answer

print("\n".join(map(str, solution(numbers))))

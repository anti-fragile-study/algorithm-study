"""
15 2
3 2 5 5 6 4 4 5 7 1 1 2 2 3 3
-> 12 (5 6 4 4 5 7 1 1 2 2 3 3)

5 1
2 2 2 2 2
-> 1
"""

import sys
from collections import defaultdict

input = lambda:sys.stdin.readline().strip()

n, k = map(int, input().split())
sequence = tuple(map(int, input().split()))

def solution(sequence, k):
    answer = 0

    counter = defaultdict(int)
    left, right = 0, 0
    while right < n:
        # right가 가리키는 원소가 현재 k개 이상이라면 left가 가리키는 원소를 제외하고 left 증가
        if counter[sequence[right]] >= k:
            counter[sequence[left]] -= 1
            left += 1
        else: # 그렇지 않으면 right가 가리키는 원소를 포함시키고 right 증가
            counter[sequence[right]] += 1
            right += 1

        answer = max(answer, right - left)

    return answer

print(solution(sequence, k))

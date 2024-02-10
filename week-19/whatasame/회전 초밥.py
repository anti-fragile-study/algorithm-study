# 같은 번호 가능

# k개 연속 시 정액 가격
# 특정 초밥 무료 -> 없어도 제공

# k개를 연속으로 먹을 때, 손님이 먹을 수 있는 초밥 종류 최댓값 반환

"""
k개 연속으로 먹고 c번을 먹는 경우
-> 예제1

k개를 연속으로 먹었는데 c를 이미 먹은 경우
-> 예제2

애초에 c가 없는 경우
5 4 3 777
5
3
2
5
1
-> 4?
"""

import sys

input = sys.stdin.readline
n, d, k, c = map(int, input().split())
numbers = [int(input()) for _ in range(n)]

from collections import defaultdict

def solution():
    kinds = defaultdict(int)
    for num in numbers[0:k]:
        kinds[num] += 1
    kinds[c] = 1
    left, right = 0, k - 1

    answer = len(kinds.keys())
    for _ in range(n):
        right = (right + 1) % n

        kinds[numbers[left]] -= 1
        if kinds[numbers[left]] == 0:
            del kinds[numbers[left]]
        kinds[numbers[right]] += 1
        kinds[c] = 1

        answer = max(answer, len(kinds.keys()))

        left = (left + 1) % n

    return answer

print(solution())

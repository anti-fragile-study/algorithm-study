"""
5 0
7 4 2 4 5
-> 1

5 27
7 4 2 4 5
-> 4

5 7
7 4 2 4 5
-> 2
"""

import sys

input = lambda:sys.stdin.readline().strip()

n, k = map(int, input().split())
courses = list(map(int, input().split()))

def solution(courses):
    # 왕복 누적 거리를 모두 구한다. -> O(n)
    cumulative, distance = [], 0
    for idx, course in enumerate(courses):
        distance += course

        cumulative.append((idx + 1, distance))
    for idx, course in enumerate(courses[::-1]):
        distance += course

        cumulative.append((len(courses) - idx, distance))

    # 왕복 누적 거리를 모두 찾아가며 속한 코스의 길이를 찾는다. -> O(n)
    for num, distance in cumulative: # distance: except right
        if distance > k:
            return num

print(solution(courses))

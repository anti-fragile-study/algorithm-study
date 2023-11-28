핑  # 진열대의 모든 보석을 구매할 수 있는 구간의 시작과 끝을 반환

"""
["A", "A", "B", "A", "A", "C", "A", "B", "C"]
-> [6, 8]

["A", "B", "A", "A", "C", "B", "A", "B", "A", "C"]
-> [4, 6]

["AAA"]
-> [1, 1]
"""

from collections import defaultdict


def solution(gems):
    n = len(gems)
    max_kinds = len(set(gems))
    start, end = 0, len(gems) - 1

    section = defaultdict(int)
    section[gems[0]] += 1

    right = 0
    for left in range(n):
        while right < n and not max_kinds == len(section):
            right += 1

            if right < n:
                section[gems[right]] += 1

        if right == n:
            break

        if end - start + 1 > right - left + 1:
            end, start = right, left

        if section[gems[left]] == 1:
            del section[gems[left]]
        else:
            section[gems[left]] -= 1

    return [start + 1, end + 1]

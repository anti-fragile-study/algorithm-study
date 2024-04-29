# 회문: 0, 유사 회문: 1, 일반: 2

"""
7
aba
faba
abaf
afba
abba
abzxba
abzyxba
-> 0 1 1 1 0 1 2
"""

import sys

input = lambda: sys.stdin.readline().strip()

t = int(input())
strings = [input() for _ in range(t)]


def solution(strings):
    return [check(string) for string in strings]

def check(string, is_deleted = False):
    left, right = 0, len(string) - 1

    while left < right:
        # 좌우가 같다면 회문일 수 있음
        if string[left] == string[right]:
            left, right = left + 1, right - 1
            continue

        # 이미 글자를 하나 지웠다면 무조건 일반 문자열
        if is_deleted:
            return 2

        # 글자를 지워서 회문이 되는지 확인
        if check(string[left + 1:right + 1], True) == 0 or check(string[left:right], True) == 0:
            return 1
        else: # 그래도 안되면 일반 문자열
            return 2

    return 0 # 회문

print("\n".join(map(str, solution(strings))))

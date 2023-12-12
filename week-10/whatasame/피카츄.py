import sys


def solution(s):
    for word in ("pi", "ka", "chu"):
        s = s.replace(word, " ")

    if s.strip():
        return "NO"

    return "YES"


input = sys.stdin.readline
s = input()

answer = solution(s)

print(answer)

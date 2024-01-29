import sys

sys.setrecursionlimit(10 ** 6)


def solution(a, b, n):
    if a > n:
        return 0
    return (n // a * b) + solution(a, b, n % a + (n // a * b))

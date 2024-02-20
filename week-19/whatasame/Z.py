# 2^n array

"""
3 0 0
-> 0

3 7 7
-> 63

3 5 1
-> 35

3 4 0
-> 32
"""

import sys

input = sys.stdin.readline
n, r, c = map(int, input().split())

def solution(n, r, c):
    length = 2 ** n

    def recu(r, c, br, bc, length):
        if length == 1:
            return 1

        result = 0
        half = length // 2
        for idx, (dr, dc) in enumerate([(0, 0), (0, half), (half, 0), (half, half)]):
            nr, nc = br + dr, bc + dc

            if not (nr <= r < nr + half and nc <= c < nc + half):
                continue

            result += half ** 2 * (idx)
            result += recu(r, c, nr, nc, half)

            return result

    return recu(r, c, 0, 0, length) - 1 # 0-indexed

sys.setrecursionlimit(10000)
print(solution(n, r, c))

# n, m은 짝수

"""
4 2 1
1 6
7 2
1 8
7 4
?
"""

import sys

input = sys.stdin.readline
n, m, r = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
operations = list(map(int, input().split()))


def solution(n, m, r, arr, operations):
    functions = [None, first, second, third, fourth, fifth, sixth]

    vertical, horizontal, angle = False, False, 0
    positions = [0, 1, 2, 3]

    for operation in operations:
        vertical, horizontal, angle, positions = functions[operation](vertical, horizontal, angle, positions)

    # 원본 배열 0, 1, 2, 3으로 쪼개기
    zero = [[col for col in row[:m // 2]] for row in arr[:n // 2]]
    one = [[col for col in row[m // 2:]] for row in arr[:n // 2]]
    two = [[col for col in row[:m // 2]] for row in arr[n // 2:]]
    three = [[col for col in row[m // 2:]] for row in arr[n // 2:]]
    divided = [zero, one, two, three]

    # 서브배열 회전 시키기
    divided = [rotate(divide, angle) for divide in divided]

    # 원래대로 합치기
    new_arr = []
    for left, right in zip(divided[positions[0]], divided[positions[1]]):
        left.extend(right)
        new_arr.append(left)
    for left, right in zip(divided[positions[2]], divided[positions[3]]):
        left.extend(right)
        new_arr.append(left)

    # 상하반전, 좌우반전 진행
    if vertical:
        new_arr = new_arr[::-1]
    if horizontal:
        new_arr = [row[::-1] for row in new_arr]

    # 최종 배열 반환
    return new_arr


# 오른쪽으로 90도 서브배열 n번 회전
def rotate(arr, n):
    for _ in range(n):
        n, m = len(arr), len(arr[0])
        new_arr = [[0 for _ in range(n)] for _ in range(m)]

        for r in range(n):
            for c in range(m):
                new_arr[c][(n - 1) - r] = arr[r][c]

        arr = new_arr

    return arr


# 상하반전
def first(vertical, horizontal, angle, pos):
    return not vertical, horizontal, angle, pos


# 좌우반전
def second(vertical, horizontal, angle, pos):
    return vertical, not horizontal, angle, pos


# 오른쪽 90도
def third(vertical, horizontal, angle, pos):
    new_angle = (angle + 1) % 4
    return vertical, horizontal, new_angle, [pos[2], pos[0], pos[3], pos[1]]


# 왼쪽 90도
def fourth(vertical, horizontal, angle, pos):
    new_angle = (angle - 1) % 4
    return vertical, horizontal, new_angle, [pos[1], pos[3], pos[0], pos[2]]


# 4사분면 오른쪽 90도
def fifth(vertical, horizontal, angle, pos):
    return vertical, horizontal, angle, [pos[2], pos[0], pos[3], pos[1]]


# 4사분면 왼쪽 90도
def sixth(vertical, horizontal, angle, pos):
    return vertical, horizontal, angle, [pos[1], pos[3], pos[0], pos[2]]


answer = solution(n, m, r, arr, operations)

print("\n".join([" ".join(map(str, row)) for row in answer]))

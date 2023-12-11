# n, m은 짝수

"""
4 6 2
10 11 12 20 21 22
13 14 15 23 24 25
30 31 32 40 41 42
33 34 35 43 44 45
?
"""

import sys

input = sys.stdin.readline
n, m, r = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
operations = list(map(int, input().split()))


def solution(n, m, r, arr, operations):
    functions = [None, first, second, third, fourth, fifth, sixth]

    # 부분그룹번호, 상하반전, 좌우반전, 오른쪽회전
    sections = [[num, False, False, 0] for num in range(4)]

    for operation in operations:
        sections = functions[operation](sections)

    # 원본 배열 0, 1, 2, 3으로 쪼개기
    zero = [[col for col in row[:m // 2]] for row in arr[:n // 2]]
    one = [[col for col in row[m // 2:]] for row in arr[:n // 2]]
    two = [[col for col in row[:m // 2]] for row in arr[n // 2:]]
    three = [[col for col in row[m // 2:]] for row in arr[n // 2:]]
    divided = [zero, one, two, three]

    for i in range(4):
        num, vertical, horizontal, angle = sections[i]

        # 서브배열 상하반전
        if vertical:
            divided[num] = divided[num][::-1]

        # 서브배열 좌우반전
        if horizontal:
            divided[num] = [row[::-1] for row in divided[num]]

        # 서브배열 회전 시키기
        divided[num] = rotate(divided[num], angle)

    # 원래대로 합치기
    new_arr = []
    for left, right in zip(divided[sections[0][0]], divided[sections[1][0]]):
        left.extend(right)
        new_arr.append(left)
    for left, right in zip(divided[sections[2][0]], divided[sections[3][0]]):
        left.extend(right)
        new_arr.append(left)

    # 최종 배열 반환
    return new_arr


# 오른쪽으로 90도 서브배열 n번 회전
def rotate(arr, cnt):
    for _ in range(cnt):
        n, m = len(arr), len(arr[0])
        new_arr = [[0 for _ in range(n)] for _ in range(m)]

        for r in range(n):
            for c in range(m):
                new_arr[c][(n - 1) - r] = arr[r][c]

        arr = new_arr

    return arr


# 상하반전
def first(sections):
    return [
        [sections[2][0], not sections[2][1], sections[2][2], sections[2][3]],
        [sections[3][0], not sections[3][1], sections[3][2], sections[3][3]],
        [sections[0][0], not sections[0][1], sections[0][2], sections[0][3]],
        [sections[1][0], not sections[1][1], sections[1][2], sections[1][3]],
    ]


# 좌우반전
def second(sections):
    return [
        [sections[1][0], sections[1][1], not sections[1][2], sections[1][3]],
        [sections[0][0], sections[0][1], not sections[0][2], sections[0][3]],
        [sections[3][0], sections[3][1], not sections[3][2], sections[3][3]],
        [sections[2][0], sections[2][1], not sections[2][2], sections[2][3]],
    ]


# 오른쪽 90도
def third(sections):
    return [
        [sections[2][0], sections[2][1], sections[2][2], (sections[2][3] + 1) % 4],
        [sections[0][0], sections[0][1], sections[0][2], (sections[0][3] + 1) % 4],
        [sections[3][0], sections[3][1], sections[3][2], (sections[3][3] + 1) % 4],
        [sections[1][0], sections[1][1], sections[1][2], (sections[1][3] + 1) % 4],
    ]


# 왼쪽 90도
def fourth(sections):
    return [
        [sections[1][0], sections[1][1], sections[1][2], (sections[1][3] - 1) % 4],
        [sections[3][0], sections[3][1], sections[3][2], (sections[3][3] - 1) % 4],
        [sections[0][0], sections[0][1], sections[0][2], (sections[0][3] - 1) % 4],
        [sections[2][0], sections[2][1], sections[2][2], (sections[2][3] - 1) % 4],
    ]


# 4사분면 오른쪽 90도
def fifth(sections):
    return [
        [sections[2][0], sections[2][1], sections[2][2], sections[2][3]],
        [sections[0][0], sections[0][1], sections[0][2], sections[0][3]],
        [sections[3][0], sections[3][1], sections[3][2], sections[3][3]],
        [sections[1][0], sections[1][1], sections[1][2], sections[1][3]],
    ]


# 4사분면 왼쪽 90도
def sixth(sections):
    return [
        [sections[1][0], sections[1][1], sections[1][2], sections[1][3]],
        [sections[3][0], sections[3][1], sections[3][2], sections[3][3]],
        [sections[0][0], sections[0][1], sections[0][2], sections[0][3]],
        [sections[2][0], sections[2][1], sections[2][2], sections[2][3]],
    ]


answer = solution(n, m, r, arr, operations)

print("\n".join([" ".join(map(str, row)) for row in answer]))

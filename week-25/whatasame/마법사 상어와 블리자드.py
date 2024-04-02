# n is odd
# (1, 1) ~ (n, n)
# 마법사 상어: 정중앙
# 블리자드를 쓰면 d방향으로 s개가 없어진다
# 연속하는 구슬이 4개 이상 있을 때 폭발한다
# 연속하는 구슬의 개수와 번호로 변한다.

# 블리자드를 M번 시전했을 때 폭발한 구슬의 개수 * 각 구슬의 번호의 합을 반환

# 구슬이 이동하는 건 리스트로 이동시키고 리스트를 다시 배열로 바꾸는 방식은 어떨까

"""
3 1
0 0 0
0 0 0
0 0 0
1 1
-> 0
"""

import sys
from collections import deque

input = lambda: sys.stdin.readline().strip()

n, m = map(int, input().split())
a = [list(map(int, input().split())) for _ in range(n)]
b = [tuple(map(int, input().split())) for _ in range(m)]

directions = {1: (-1, 0, 4), 2: (1, 0, 3), 3: (0, -1, 1), 4: (0, 1, 2)} # (dr, dc, next_direction)


def solution(arr, blizzards):
    answer = {1: 0, 2: 0, 3: 0}

    # 상어 위치 초기화
    center = n // 2
    arr[center][center] = -1

    # 각 블리자드에 대하여
    for d, s in blizzards:
        # 블리자드 사용
        r, c = center, center
        dr, dc, _ = directions[d]
        for _ in range(s):
            r, c = r + dr, c + dc
            arr[r][c] = 0

        # 리스트로 변환
        beads = to_beads(arr)  # 밖에서 안으로

        # 연속하는 구슬 폭발
        beads = explode(answer, beads)

        # 구슬 변화
        reversed_line = []  # 안에서 밖으로
        num, count = None, 0
        for idx, val in enumerate(beads[::-1]):
            if num is None:
                num, count = val, 1
                continue

            if val != num:
                reversed_line.append(count)
                reversed_line.append(num)
                num, count = val, 1
            else:
                count += 1
        reversed_line.append(count)
        reversed_line.append(num)

        reversed_line = [-1] + reversed_line  # 상어 붙여주기
        reversed_line = reversed_line[:n ** 2]  # 길이 제한
        reversed_line = reversed_line + [0] * (n ** 2 - len(reversed_line))  # 부족한 개수는 0으로 채워넣기

        # 배열로 변환
        arr = to_arr(reversed_line[::-1])

    return answer[1] + 2 * answer[2] + 3 * answer[3]


def to_beads(arr):
    beads = deque()
    r, c = 0, 0

    dr, dc, nd = directions[4] # 오른쪽을 향해 이동
    while arr[r][c] != -1:  # 상어 제외
        # 값 추가
        if arr[r][c] > 0:
            beads.append(arr[r][c])
        arr[r][c] = None # 방문 처리

        # out of idx이거나 방문한 곳이라면 방향 전환
        if not (0 <= r + dr < n and 0 <= c + dc < n) or arr[r + dr][c + dc] is None:
            dr, dc, nd = directions[nd]

        # 이동
        r, c = r + dr, c + dc

    return beads


def explode(answer, line):
    # 연속되는 그룹 찾기
    exploded = False
    num, indices = None, []
    for idx, val in enumerate(line):
        # 다른 숫자거나 마지막 원소일 때 그룹 구분
        if num is None:
            num, indices = val, [idx]
            continue
        if val != num:
            # 조건에 맞으면 폭발
            if len(indices) >= 4:
                for i in indices:
                    line[i] = 0
                answer[num] += len(indices)
                exploded = True
            num, indices = val, [idx]
        else:
            indices.append(idx)
    if len(indices) >= 4:
        for i in indices:
            line[i] = 0
        answer[num] += len(indices)
        exploded = True

    # 0으로 표시된 원소 제거
    line = [val for val in line if val != 0]

    # 폭발한 대상이 없다면 재귀 종료
    if not exploded:
        return line

    # 재귀적으로 수행
    return explode(answer, line)


def to_arr(beads):
    r, c = 0, 0
    arr = [[None for _ in range(n)] for _ in range(n)]

    dr, dc, nd = directions[4] # 오른쪽을 향해 이동
    for val in beads:
        # 값 추가
        arr[r][c] = val

        # out of idx이거나 방문한 곳이라면 방향 전환
        if not (0 <= r + dr < n and 0 <= c + dc < n) or arr[r + dr][c + dc] is not None:
            dr, dc, nd = directions[nd]

        # 이동
        r, c = r + dr, c + dc

    return arr


print(solution(a, b))

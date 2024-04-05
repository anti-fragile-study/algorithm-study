# 3, 4면이 바다면 사라진다
# 모든 섬을 포함하는 가장 작은 직사각형을 반환

"""
5 3
XXX
XXX
XXX
XXX
XXX
-> same

3 2
XXX
...
XXX
->
X
.
X
"""

import sys, copy

input = lambda:sys.stdin.readline().strip()

n, m = map(int, input().split())
arr = [list(input()) for _ in range(n)]

def solution(arr):
    # 배열 주위로 한 겹 더 싼다
    arr = [["."] * (m + 2), *[["."] + row + ["."] for row in arr], ["."] * (m + 2)]
    result = copy.deepcopy(arr)

    # 조건에 맞게 땅을 없앤다
    directions = [(0, 1), (0, -1), (-1, 0), (1, 0)]
    for r in range(len(arr)):
        for c in range(len(arr[0])):
            # 바다면 스킵
            if arr[r][c] == ".":
                continue

            # 땅이면 조건 확인 후 삭제
            cnt = 0
            for dr, dc in directions:
                nr, nc = r + dr, c + dc

                if arr[nr][nc] == ".":
                    cnt += 1

            if cnt >= 3:
                result[r][c] = "."

    # 모서리 좌표를 갱신한다
    left, right, up, down = m, 0, n, 0
    for r in range(len(result)):
        for c in range(len(result[0])):
            if result[r][c] == "X":
                left, right, up, down = min(left, c), max(right, c), min(up, r), max(down, r)

    return [row[left:right+1] for row in result[up:down+1]]

print("\n".join("".join(map(str, row)) for row in solution(arr)))

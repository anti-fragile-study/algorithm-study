# 검은 블록을 떨어트려 직사각형이 된다면 펑

# 없앨 수 있는 블록 개수의 최댓값을 반환

"""
[
    [0, 0, 0, 0, 3],
    [0, 0, 0, 3, 3],
    [0, 0, 0, 2, 3],
    [1, 2, 2, 2, 0],
    [1, 1, 1, 0, 0]
]
-> 2


[
    [0, 0, 0, 0, 0],
    [0, 3, 3, 3, 0],
    [0, 0, 3, 2, 0],
    [1, 2, 2, 2, 0],
    [1, 1, 1, 0, 0]
]
-> 0


[
    [0, 0, 3, 0, 0],
    [0, 0, 3, 3, 0],
    [0, 0, 3, 2, 0],
    [1, 2, 2, 2, 0],
    [1, 1, 1, 0, 0]
]
-> 0

[
    [0, 0, 1, 1, 1],
    [0, 0, 0, 1, 0],
    [3, 0, 0, 2, 0],
    [3, 2, 2, 2, 0],
    [3, 3, 0, 0, 0]]
-> 0
"""

from collections import *

directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]

def solution(board):
    possible = {
        frozenset([(0, 0), (1, 0), (1, 1), (1, 2)]): {(0, 1), (0, 2)},
        frozenset([(0, 0), (1, 0), (2, 0), (2, -1)]): {(0, -1), (1, -1)},
        frozenset([(0, 0), (1, 0), (1, -1), (1, -2)]): {(0, -1), (0, -2)},
        frozenset([(0, 0), (1, 0), (2, 0), (2, 1)]): {(0, 1), (1, 1)},
        frozenset([(0, 0), (1, 0), (1, -1), (1, 1)]): {(0, -1), (0, 1)}
    }

    # 무조건 불가능한 블록들을 찾는다.
    n = len(board)
    visited = [[False for _ in range(n)] for _ in range(n)]

    def dfs(sr, sc, val):
        path = [(sr, sc)]
        q = deque([(sr, sc)])
        visited[sr][sc] = True

        while q:
            r, c = q.popleft()

            for dr, dc in directions:
                nr, nc = r + dr, c + dc

                if not 0 <= nr < n or not 0 <= nc < n or visited[nr][nc] or board[nr][nc] != val:
                    continue

                path.append((nr, nc))
                q.append((nr, nc))
                visited[nr][nc] = True

        return path

    # 가능할 수 있는 블록들을 찾는다.
    candidate = [] # (시작점, {채워야하는 곳})
    disable = [[False for _ in range(n)] for _ in range(n)]
    def mark(path):
        for r, c in path:
            for nr in range(r, n):
                disable[nr][c] = True

    for r in range(n):
        for c in range(n):
            if board[r][c] == 0 or visited[r][c] :
                continue
            path = dfs(r, c, board[r][c])
            nomalized_path = nomalize(path)
            if nomalized_path in possible:
                candidate.append((path, possible[nomalized_path]))
            # 무조건 불가능한 블록 아래에 있는 칸들을 표시한다.
            else:
                mark(path)

    # 가능할 수 있는 블록들을 직사각형으로 만들 수 있는 칸이 표시되어 있지 않다면 없앨 수 있다.
    answer = 0
    for path, blanks in candidate:
        sr, sc = path[0]
        removable = True
        for dr, dc in blanks:
            if disable[sr + dr][sc + dc]:
                removable = False
                mark(path)
                break
        if removable:
            answer += 1

    return answer

def nomalize(arr):
    br, bc = arr[0]

    return frozenset((r - br, c - bc) for r, c in arr)

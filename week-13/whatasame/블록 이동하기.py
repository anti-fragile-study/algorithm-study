# (1, 1) -> (N, N)
# 0: 빈칸
# 1: 벽

# 로봇이 차지하는 두 칸 중 하나라도 (N, N) 이면 OK

# 상하좌우 이동 가능

# 시계 혹은 반시계 방향으로 90도 회전 가능
# 회전 방향에 벽이 없어야함
# 회전 시 1초 소모

# 이동 경우의 수: 상하좌우, 왼팔 축방향, 오른팔 축방향

# 도착까지 걸리는 최소 시간을 반환

"""
[
    [0, 0, 0, 0, 0],
    [1, 1, 0, 0, 0],
    [0, 0, 0, 0, 0],
    [0, 0, 1, 1, 0],
    [0, 0, 0, 0, 0]
]
-> 15
"""

from collections import *

def solution(board):
    # 비용을 저장하는 사전 선언 = { {(r1, c1), (r2, c2)} : cost)}
    costs = {}

    # BFS 순회 (큐 요소: 로봇 팔 한 짝씩)
    now = frozenset([(0, 0), (0, 1)])
    q = deque()
    q.append((now, 0))
    costs[now] = 0

    while q:
        pos, cost = q.popleft()
        p1, p2 = pos

        # 큐에 담는 조건: 이동 가능 or 회전 가능 and 새로운 비용 < 기존 비용
        for np1, np2 in esnw(p1, p2) + cw(board, p1, p2) + rcw(board, p1, p2):
            nr1, nc1 = np1
            nr2, nc2 = np2
            if not movable(board, nr1, nc1) or \
                    not movable(board, nr2, nc2):
                continue

            npos = frozenset((np1, np2))
            if not npos in costs or costs[npos] > cost + 1:
                q.append((npos, cost + 1))
                costs[npos] = cost + 1

    n = len(board)
    for k, v in costs.items():
        if (n - 1, n - 1) in k:
            return v


# 동서남북 이동
def esnw(p1, p2):
    r1, c1 = p1
    r2, c2 = p2

    return [
        [(r1, c1 + 1), (r2, c2 + 1)],
        [(r1, c1 - 1), (r2, c2 - 1)],
        [(r1 + 1, c1), (r2 + 1, c2)],
        [(r1 - 1, c1), (r2 - 1, c2)],
    ]

# 시계방향 회전
def cw(board, p1, p2):
    r1, c1 = p1
    r2, c2 = p2

    rotate = []
    # ㅡ
    if r1 == r2:
        # 왼팔 축 회전
        r, c = min(p1, p2)
        if movable(board, r + 1, c + 1):
            rotate.append([(r, c), (r + 1, c)])

        # 오른팔 축 회전
        r, c = max(p1, p2)
        if movable(board, r - 1, c - 1):
            rotate.append([(r, c), (r - 1, c)])
    # ㅣ
    if c1 == c2:
        # 왼팔 축 회전
        r, c = min(p1, p2)
        if movable(board, r + 1, c - 1):
            rotate.append([(r, c), (r, c - 1)])
        # 오른팔 축 회전
        r, c = max(p1, p2)
        if movable(board, r - 1, c + 1):
            rotate.append([(r, c), (r, c + 1)])

    return rotate

# 반시계 방향 회전
def rcw(board, p1, p2):
    r1, c1 = p1
    r2, c2 = p2

    rotate = []
    # ㅡ
    if r1 == r2:
        # 왼팔 축 회전
        r, c = min(p1, p2)
        if movable(board, r - 1, c + 1):
            rotate.append([(r, c), (r - 1, c)])

        # 오른팔 축 회전
        r, c = max(p1, p2)
        if movable(board, r + 1, c - 1):
            rotate.append([(r, c), (r + 1, c)])
    # ㅣ
    if c1 == c2:
        # 왼팔 축 회전
        r, c = min(p1, p2)
        if movable(board, r + 1, c + 1):
            rotate.append([(r, c), (r, c + 1)])
        # 오른팔 축 회전
        r, c = max(p1, p2)
        if movable(board, r - 1, c - 1):
            rotate.append([(r, c), (r, c - 1)])

    return rotate

def movable(board, r, c):
    n = len(board)

    return 0 <= r < n and 0 <= c < n and board[r][c] == 0

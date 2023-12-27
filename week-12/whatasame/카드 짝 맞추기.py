# 8종 2장씩 -> 1세트씩 밖에 없다
# 일반 이동: 상하좌우
# Ctrl 이동: 가장 가까운 카드, 카드가 하나도 없다면 끝 칸으로 이동
# 빈 칸도 없다면 가만히

# 어디든 최대 3회 이내로 갈 수 있다.

# Ctrl -> 이동 / Enter: 뒤집기

# 남은 카드를 모두 제거하는데 필요한 Enter키와 방향키 합의 최솟값을 반환

# 캐릭터 최대 12개 (6세트) -> 빈칸 4개
"""
[
    [0, 0, 0, 0],
    [0, 1, 2, 0],
    [0, 2, 1, 0],
    [0, 0, 0, 0]
]
3
3
-> 11

[
    [0, 0, 0, 0],
    [1, 2, 2, 1],
    [0, 0, 0, 0],
    [0, 0, 0, 0]
]
1
1
-> 7
"""

from collections import *
from itertools import *

directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]

def solution(board, r, c):
    answer = 0

    # 각 세트의 위치를 구한다 pos_map = { num : [(r1, c1), (r2, c2)]}
    pos_map = defaultdict(list)
    for i in range(4):
        for j in range(4):
            if board[i][j] != 0:
                pos_map[board[i][j]].append((i, j))

    # pos_map의 키가 있을 때까지
    while pos_map:
        # 현재 위치에서 BFS(일반 이동, Ctrl 이동)을 수행해서 가장 가까운 카드 찾기
        tr, tc = bfs(board, r, c)
        # 가장 가까운 카드로 이동
        r, c, cost = move(board, r, c, tr, tc)
        answer += cost
        val = board[r][c]
        # 카드 뒤집기
        board[r][c] = 0
        answer += 1
        # pos_map에서 짝 찾기
        tr, tc = pair(pos_map[val], r, c)
        # 짝으로 이동
        r, c, cost = move(board, r, c, tr, tc)
        answer += cost
        # 카드 뒤집기
        board[r][c] = 0
        answer += 1
        # pos_map에서 키 삭제
        del pos_map[val]

    return answer

def move(board, r, c, tr, tc):
    cost = 0
    if r != tr:
        if (tr, c) in [ctrl_up(board, r, c), ctrl_down(board, r, c)]:
            cost += 1
        else:
            cost += abs(tr - r)
    if c != tc:
        if (r, tc) in [ctrl_left(board, r, c), ctrl_right(board, r, c)]:
            cost += 1
        else:
            cost += abs(tc - c)
    return tr, tc, cost

def pair(pos_list, r, c):
    return pos_list[1] if pos_list[0] == (r, c) else pos_list[0]

def bfs(board, sr, sc):
    # queue에 노드 넣을 때 일반 이동과 Ctrl 이동을 같이 넣어야함
    q = deque()
    visited = [[False for _ in range(4)] for _ in range(4)]
    q.append((sr, sc))
    visited[sr][sc] = True

    while q:
        r, c = q.popleft()
        if board[r][c] != 0:
            return r, c

        # 일반 이동
        for dr, dc in directions:
            nr, nc = r + dr, c + dc

            if not movable(visited, nr, nc):
                continue
            q.append((nr, nc))
            visited[nr][nc] = True
        # Ctrl 이동
        for nr, nc in ctrls(board, r, c):
            if not movable(visited, nr, nc):
                continue
            q.append((nr, nc))
            visited[nr][nc] = True

def ctrls(board, r, c):
    return [
        ctrl_left(board, r, c),
        ctrl_right(board, r, c),
        ctrl_up(board, r, c),
        ctrl_down(board, r, c)
    ]

def ctrl_left(board, r, c):
    for i in range(c, -1, -1):
        if board[r][i] != 0:
            return r, i
    return r, 0

def ctrl_right(board, r, c):
    for i in range(4 - c):
        if board[r][c + i]:
            return r, c + i
    return r, 3

def ctrl_up(board, r, c):
    for i in range(r, -1, -1):
        if board[i][c] != 0:
            return i, c
    return 0, c

def ctrl_down(board, r, c):
    for i in range(4 - r):
        if board[r + i][c]:
            return r + i, c
    return 3, c

def movable(visited, r, c):
    return 0 <= r < 4 and 0 <= c < 4 and visited[r][c] == False

# 한 턴에 2알씩 둔다.
# 그룹: 상하좌우로 인접한 같은 색
# 그룹의 돌 중 빈 칸과 인접한 돌이 하나도 없으면 죽음
# 스스로 잡히는 곳에도 둘 수 있다. 이때, 조건만 맞다면 다른 돌을 죽일 수 있다.

# 진행중인 바둑판이 주어진다.
# 돌 2개를 둘 때, 최대로 죽일 수 있는 알의 개수를 출력

# 0: 빈칸
# 1: 나
# 2: 상대

"""
(1칸 1칸), (2칸) 중 전자가 최대
4 4
2 1 2 2
2 1 1 0
0 0 0 1
0 0 2 2
-> 4

(1칸 1칸), (2칸) 중 후자가 최대
4 4
2 1 1 2
2 1 0 0
2 0 0 0
0 0 1 2
-> 3
(1칸 1칸)으로 최대
-> 예제 5

(2칸)으로 최대
-> 예제 4

부족해서 0인 경우
-> 예제 2
"""

import sys
from collections import deque, defaultdict

input = sys.stdin.readline
n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def solution(n, m, board):
    # 상대방 바둑알 그룹 찾기
    visited = [[False for _ in range(m)] for _ in range(n)]

    candidates = defaultdict(int)
    for r in range(n):
        for c in range(m):
            if not visited[r][c] and board[r][c] == 2:
                blanks, count = bfs(board, visited, r, c)
                if len(blanks) <= 2:
                    candidates[blanks] += count

    # (1칸, 1칸), (2칸) 분리
    twos = [k for k in candidates.keys() if len(k) == 2]
    ones = [k for k in candidates.keys() if len(k) == 1]

    # (1칸, 1칸), (2칸) 선택 시 최대 개수 구하기
    choose_one, choose_two = 0, 0
    if ones:
        choose_one = sum(sorted(map(lambda x: candidates[x], ones), reverse=True)[:2])
    if twos:
        p1, p2 = max(twos, key=lambda x: candidates[x])
        choose_two = candidates[frozenset([p1, p2])] + candidates[frozenset([p1])] + candidates[frozenset([p2])]

    return max(choose_one, choose_two)


def bfs(board, visited, sr, sc):
    blanks, count = set(), 1  # 채워야하는 빈 칸들의 좌표, 그룹 내 바둑알 개수

    q = deque()
    q.append((sr, sc))
    visited[sr][sc] = True  # 방문 처리

    while q:
        r, c = q.popleft()

        for dr, dc in directions:
            nr, nc = r + dr, c + dc

            # 방문 가능 검사
            if nr < 0 or nr >= n or nc < 0 or nc >= m or visited[nr][nc]:
                continue

            # 빈 칸 기록 -> 중복 방문 허용
            if board[nr][nc] == 0 and (nr, nc) not in blanks:
                blanks.add((nr, nc))

            # 상대 바둑알 세기
            if board[nr][nc] == 1:
                visited[nr][nc] = True

            if board[nr][nc] == 2:
                count += 1
                q.append((nr, nc))
                visited[nr][nc] = True

    return frozenset(blanks), count


answer = solution(n, m, board)

print(answer)

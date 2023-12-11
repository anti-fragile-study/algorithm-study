import sys
from collections import deque, defaultdict
from itertools import combinations


def find_neighbors(r, c, N, M):
    neighbors = []
    if r > 0:
        neighbors.append((r - 1, c))
    if r < N - 1:
        neighbors.append((r + 1, c))
    if c > 0:
        neighbors.append((r, c - 1))
    if c < M - 1:
        neighbors.append((r, c + 1))
    return neighbors


def solve():
    N, M = map(int, sys.stdin.readline().split())

    board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    visited = [[False for _ in range(M)] for _ in range(N)]
    candidate = set()
    data = defaultdict(int)
    answer = 0

    for r in range(N):
        for c in range(M):
            if visited[r][c]:
                continue

            visited[r][c] = True

            if board[r][c] != 2:
                continue

            queue = deque()
            queue.append((r, c))
            zeros = set()
            twos = 0

            while queue:
                row, col = queue.popleft()
                twos += 1

                for next_r, next_c in find_neighbors(row, col, N, M):
                    if board[next_r][next_c] == 2 and not visited[next_r][next_c]:
                        queue.append((next_r, next_c))
                        visited[next_r][next_c] = True
                    elif board[next_r][next_c] == 0:
                        zeros.add((next_r, next_c))

            if len(zeros) <= 2:
                candidate = candidate.union(zeros)
                data[frozenset(zeros)] += twos

    if len(candidate) == 1:
        print(data[frozenset(candidate)])
        return

    for p1, p2 in combinations(candidate, 2):
        stones = data[frozenset([p1])] + data[frozenset([p2])] + data[frozenset([p1, p2])]

        if stones > answer:
            answer = stones

    print(answer)


solve()

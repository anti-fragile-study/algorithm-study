from heapq import heappush, heappop
import math


def solution(board):
    N = len(board)
    directions = [(0, 1), (1, 0), (0, -1), (-1, 0)]
    heap = []
    heappush(heap, (0, 0, 0, -1))

    visited = [[[math.inf for _ in range(4)] for _ in range(N)] for _ in range(N)]

    while heap:
        cost, r, c, prev_d = heappop(heap)

        for i, d in enumerate(directions):
            next_r = r + d[0]
            next_c = c + d[1]

            if 0 <= next_r < N and 0 <= next_c < N and board[next_r][next_c] != 1:
                if prev_d == i or (r == 0 and c == 0):
                    diff = 100
                else:
                    diff = 600

                if visited[next_r][next_c][i] > cost + diff:
                    heappush(heap, (cost + diff, next_r, next_c, i))
                    visited[next_r][next_c][i] = cost + diff

    return min(visited[N - 1][N - 1])

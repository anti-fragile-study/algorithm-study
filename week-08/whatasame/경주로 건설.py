# (0, 0) ~ (n-1, n-1) 경로
# 직선 - 100원
# 코너 - 500원
# 0: 갈 수 있음
# 1: 벽

# 직선 도로의 개수가 많아도 더 쌀 수 있음 -> 최단 경로가 답이 아닐 수 있음

# 최소 비용 반환

import heapq


def solution(board):
    n = len(board)

    directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]
    costs = [[[float("inf") for _ in range(4)] for _ in range(n)] for _ in range(n)]

    min_heap = [(0, 0, 0, None)]  # cost, r, c, d

    while min_heap:
        cost, r, c, d = heapq.heappop(min_heap)

        for dd, (dr, dc) in enumerate(directions):
            nr, nc = r + dr, c + dc

            # out of index
            if not (0 <= nr < n and 0 <= nc < n):
                continue

            # wall
            if board[nr][nc] == 1:
                continue

            # 비용이 더 싸다면 방문
            new_cost = cost + (100 if d == dd or d == None else 600)
            if costs[nr][nc][dd] < new_cost:
                continue

            heapq.heappush(min_heap, (new_cost, nr, nc, dd))
            costs[nr][nc][dd] = new_cost

    return min(costs[n - 1][n - 1])

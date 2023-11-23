from math import inf
from heapq import heappop, heappush

dy = [0,0,-1,1]
dx = [1,-1,0,0]

def solution(board):
  n = len(board)
  hq = [(0, 0, 0, 0), (0, 0, 0, 1)]
  visit = [[[inf, inf] for _ in range(n)] for _ in range(n)]
  visit[0][0] = [0, 0]

  while hq:
    acc, cy, cx, cdir = heappop(hq)
    for i in range(4):
      ny, nx = cy + dy[i], cx + dx[i]
      ndir = abs(dy[i])

      if 0 <= ny < n and 0 <= nx < n:
        if board[ny][nx] == 1:
          continue

        cost = acc + 100
        if cdir != ndir:
          cost += 500

        if cost < visit[ny][nx][ndir]:
          visit[ny][nx][ndir] = cost
          heappush(hq, (cost, ny, nx, ndir))

  return min(visit[n-1][n-1])
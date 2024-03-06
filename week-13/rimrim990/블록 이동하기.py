from math import inf
from collections import deque

W = 0
H = 1

dy = [0,0,1,-1]
dx = [1,-1,0,0]

def solution(board):
  n = len(board)
  visit = [[[inf, inf] for _ in range(n)] for _ in range(n)]
  visit[0][0][W] = 0
  dq = deque([(0, 0, W)])

  while dq:
    cy, cx, cd = dq.popleft()
    cost = visit[cy][cx][cd]+1

    # 한 칸 이동
    for i in range(4):
      ny = cy + dy[i]
      nx = cx + dx[i]
      if check(n, board, ny, nx, cd):
        if cost < visit[ny][nx][cd]:
          visit[ny][nx][cd] = cost
          dq.append((ny, nx, cd))

    # 세로 방향으로 회전
    if cd == W:
      for ny in [cy-1, cy+1]:
        if ok(n, board, ny, cx) and ok(n, board, ny, cx+1):
          for nx in [cx, cx+1]:
            if cost < visit[min(ny, cy)][nx][1-cd]:
              visit[min(ny, cy)][nx][1-cd] = cost
              dq.append((min(ny, cy), nx, 1-cd))

    # 가로 방향으로 회전
    if cd == H:
      for nx in [cx-1, cx+1]:
        if ok(n, board, cy, nx) and ok(n, board, cy+1, nx):
          for ny in [cy, cy+1]:
            if cost < visit[ny][min(cx, nx)][1-cd]:
              visit[ny][min(cx, nx)][1-cd] = cost
              dq.append((ny, min(cx, nx), 1-cd))

  return min(visit[n-2][n-1][H], visit[n-1][n-2][W])

def check(n, board, y, x, d):
  v1 = ok(n, board, y, x)
  if d == W:
    v2 = ok(n, board, y, x+1)
  elif d == H:
    v2 = ok(n, board, y+1, x)
  return v1 and v2

def ok(n, board, y, x):
  return 0 <= y < n and 0 <= x < n and board[y][x] == 0

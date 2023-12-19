dy = [0,0,1,-1]
dx = [1,-1,0,0]

def solution(board, aloc, bloc):
  _, cnt = search(board, [aloc, bloc], 0, 0)
  return cnt

def search(board, curs, turn, cnt):
  cy, cx = curs[turn]

  # 서있던 발판 무너짐
  if board[cy][cx] == 0:
    return 1-turn, cnt

  # 이기는 경우와 지는 경우
  wins, loses = [], []
  board[cy][cx] = 0

  for i in range(4):
    ny = cy + dy[i]
    nx = cx + dx[i]
    if check(board, ny, nx) and board[ny][nx] == 1:
      nxts = curs[:]
      nxts[turn] = [ny, nx]

      winner, dist = search(board, nxts, 1-turn, cnt+1)
      if winner == turn:
        wins.append(dist)
      else:
        loses.append(dist)

  board[cy][cx] = 1

  # 이동할 수 있는 경로 없음
  if not wins and not loses:
    return 1-turn, cnt

  # 이길 수 있으면 최단 거리 이동
  if wins:
    return turn, min(wins)

  # 이길 수 없으면 최장 거리 이동
  return 1-turn, max(loses)

def check(board, y, x):
  return 0 <= y < len(board) and 0 <= x < len(board[0])

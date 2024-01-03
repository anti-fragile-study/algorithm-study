from collections import deque

def solution(board, moves):
  n = len(board)
  dolls = [deque([]) for _ in range(n)]

  # 인형뽑기 생성
  for x in range(n):
    for y in range(n):
      if board[y][x] != 0:
        dolls[x].append(board[y][x])

  # 뽑은 인형
  pick = []
  ans = 0

  for move in moves:
    if not dolls[move-1]:
      continue

    # 인형 뽑기
    doll = dolls[move-1].popleft()
    if pick and pick[-1] == doll:
      pick.pop()
      ans += 2
    else:
      pick.append(doll)

  return ans

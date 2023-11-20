def solution(board, skill):
  n, m = len(board), len(board[0])
  acc = [[0] * (m+1) for _ in range(n+1)]

  for tp, r1, c1, r2, c2, dg in skill:
    # 공격
    if tp == 1: dg *= -1

    acc[r1][c1] += dg
    acc[r1][c2+1] -= dg
    acc[r2+1][c1] -= dg
    acc[r2+1][c2+1] += dg

  for j in range(m):
    for i in range(1, n):
      acc[i][j] += acc[i-1][j]

  answer = 0
  for i in range(n):
    for j in range(m):
      if j > 0: acc[i][j] += acc[i][j-1]
      if acc[i][j] + board[i][j] > 0: answer += 1

  return answer
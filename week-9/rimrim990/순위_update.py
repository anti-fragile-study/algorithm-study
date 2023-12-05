def solution(n, results):
  ans = [[0] * (n+1) for _ in range(n+1)]
  for win, lose in results:
    ans[win][lose] += 1
    ans[lose][win] -= 1

  # a->b, b->c 이면 a->c 다
  for k in range(1, n+1):
    for i in range(1, n+1):
      for j in range(1, n+1):
        if ans[i][k] == ans[k][j] != 0:
          ans[i][j] = ans[i][k]

  cnt = 0
  for i in range(1, n+1):
    if ans[i].count(0) == 2:
      cnt += 1

  return cnt
from math import inf

def solution(alp, cop, problems):
  n = 151; m = 151;
  dp = [[inf] * m for _ in range(n)]
  dp[alp][cop] = 0

  for i in range(alp, n):
    for j in range(cop, m):
      # 알고리즘, 코딩 공부
      if i+1 < n:
        dp[i+1][j] = min(dp[i+1][j], dp[i][j]+1)
      else:
        dp[150][j] = min(dp[150][j], dp[i][j]+1)

      if j+1 < m:
        dp[i][j+1] = min(dp[i][j+1], dp[i][j]+1)
      else:
        dp[i][150] = min(dp[i][150], dp[i][j]+1)

      # 문제 풀기
      for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
        if i < alp_req or j < cop_req:
          continue

        ni, nj = i+alp_rwd, j+cop_rwd
        # 150을 초과하는 값 필요 없음
        if ni >= n: ni = 150
        if nj >= m: nj = 150
        dp[ni][nj] = min(dp[ni][nj], dp[i][j] + cost)

  target = [0, 0]
  for p in problems:
    target[0] = max(target[0], p[0])
    target[1] = max(target[1], p[1])

  res = inf
  for i in range(target[0], n):
    for j in range(target[1], m):
      res = min(res, dp[i][j])

  return res
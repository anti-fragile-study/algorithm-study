from collections import deque

def solution(n, results):
  # 정방향, 역방향
  cnts = [[0, 0] for _ in range(n+1)]
  edges = [[[], []] for _ in range(n+1)]
  for win, lose in results:
    cnts[win][0] += 1
    cnts[lose][1] += 1
    edges[lose][0].append(win)
    edges[win][1].append(lose)

  # 내가 이긴 선수
  wins = search(n, cnts, edges, 0)
  # 나를 이긴 선수
  loses = search(n, cnts, edges, 1)

  answer = 0
  for i in range(1, n+1):
    total = wins[i] | loses[i]
    if len(total) == n:
      answer += 1

  return answer

def search(n, cnts, edges, idx):
  dq = deque([])
  res = [{i} for i in range(n+1)]

  for i in range(n+1):
    if cnts[i][idx] == 0:
      dq.append(i)

  while dq:
    cur = dq.popleft()
    for nxt in edges[cur][idx]:
      cnts[nxt][idx] -= 1
      res[nxt] |= res[cur]

      if cnts[nxt][idx] == 0:
        dq.append(nxt)

  return res
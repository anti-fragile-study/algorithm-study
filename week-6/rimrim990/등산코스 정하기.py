from math import inf
from heapq import heappop, heappush

def solution(n, paths, gates, summits):
  graph = [[] for _ in range(n+1)]
  for i, j, w in paths:
    graph[i].append((j, w))
    graph[j].append((i, w))

  min_intens = inf; summit_num = inf

  hq = []; visit = [inf for _ in range(n+1)]
  for gate in gates:
    heappush(hq, (0, gate))
    visit[gate] = 0

  while hq:
    intens, cur = heappop(hq)
    if visit[cur] < intens:
      continue

    if cur in summits:
      if min_intens < intens:
        break

      min_intens = intens
      summit_num = min(cur, summit_num)
      continue

    for nxt, cost in graph[cur]:
      nxt_intens = max(cost, intens)
      if nxt_intens >= visit[nxt]:
        continue
      visit[nxt] = nxt_intens
      heappush(hq, (nxt_intens, nxt))

  return [summit_num, min_intens]
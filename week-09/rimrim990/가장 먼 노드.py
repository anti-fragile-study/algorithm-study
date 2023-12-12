from math import inf
from heapq import heappop, heappush

def solution(n, edge):
  graph = [[] for _ in range(n+1)]
  for a, b in edge:
    graph[a].append(b)
    graph[b].append(a)

  dist = [inf for _ in range(n+1)]
  dist[1] = 0

  hq = [(0, 1)]
  while hq:
    cost, cur = heappop(hq)
    for nxt in graph[cur]:
      if cost + 1 < dist[nxt]:
        dist[nxt] = cost + 1
        heappush(hq, (dist[nxt], nxt))

  max_dist = max(dist[1:])
  return dist.count(max_dist)
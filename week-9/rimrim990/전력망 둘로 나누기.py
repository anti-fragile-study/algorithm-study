from collections import deque

def solution(n, wires):
  edges = [[] for _ in range(n+1)]
  for a, b in wires:
    edges[a].append(b)
    edges[b].append(a)

  cnt = n
  for a, b in wires:
    cnt = min(cnt, count(n, edges, a, b))

  return cnt

def count(n, edges, v1, v2):
  visit = [False for _ in range(n+1)]
  visit[v1] = True
  visit[v2] = True

  cnt = [1, 1]
  dq = deque([(v1,0), (v2, 1)])
  while dq:
    cur, idx = dq.popleft()
    for nxt in edges[cur]:
      if not visit[nxt]:
        dq.append((nxt, idx))
        cnt[idx] += 1
        visit[nxt] = True

  return abs(cnt[0] - cnt[1])

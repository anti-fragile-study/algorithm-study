# 다익스트라
from heapq import heappop, heappush

V, E, P = map(int, input().split())

graph = [[] for _ in range(V+1)]

for _ in range(E):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

def dstra(start, end):
    distance = [int(1e9)] * (V+1)
    distance[start] = 0
    q = []
    heappush(q, (0, start))
    while q:
        dist, now = heappop(q)

        if distance[now] < dist:
            continue
        
        if now == end:
            return dist

        for nxt, d in graph[now]:
            _dist = dist + d
            if distance[nxt] > _dist:
                distance[nxt] = _dist
                heappush(q, (_dist, nxt))

if dstra(1, V) == (dstra(1, P) + dstra(P, V)):
    print("SAVE HIM")
else:
    print("GOOD BYE")
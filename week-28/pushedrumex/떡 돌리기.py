import sys
from heapq import heappop, heappush

input = sys.stdin.readline

INF = int(1e9)
N, M, X, Y = map(int, input().split())

graph = [[] for _ in range(N)]
for _ in range(M):
    A, B, C = map(int, input().split())
    graph[A].append((C*2, B))
    graph[B].append((C*2, A))

# 성현이 집에서 모든 집까지 최소 거리 -> 다익스트라

distance = [INF] * N
distance[Y] = 0

q = []
heappush(q, (0, Y))
while q:
    dist, now = heappop(q)

    if distance[now] < dist:
        continue

    for d, next in graph[now]:
        d += dist
        if distance[next] > d:
            distance[next] = d
            heappush(q, (d, next))

distance.sort(reverse=True)
if distance[0] > X:
    print(-1)
    exit()

answer = 1
dist = 0
while distance:
    if dist + distance[-1] <= X:
        dist += distance.pop()
    else:
        dist = 0
        answer += 1

print(answer)
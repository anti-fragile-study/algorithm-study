import heapq, sys
input = sys.stdin.readline

N, M = map(int, input().split())
sight = list(map(int, input().split()))

graph = [[] for _ in range(N)]
distance = [int(1e10)] * N

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

q = []
heapq.heappush(q, (0, 0))
distance[0] = 0

while q:
    now, dist = heapq.heappop(q)
    if distance[now] < dist:
        continue
    for node, d in graph[now]:
        d += dist
        if d < distance[node] and (node == N-1 or sight[node] != 1):
            distance[node] = d
            heapq.heappush(q, (node, d))

print(distance[N-1]) if distance[N-1] != int(1e10) else print(-1)
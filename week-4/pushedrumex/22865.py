import heapq, sys
input = sys.stdin.readline
INF = int(1e9)

N = int(input())
A, B, C = map(int, input().split())
M = int(input())

distance_a = [INF] * (N + 1)
distance_b = [INF] * (N + 1)
distance_c = [INF] * (N + 1)

graph = [[] for _ in range(N + 1)]
for _ in range(M):
    D, E, L = map(int, input().split())
    graph[D].append((E, L))
    graph[E].append((D, L))

def dijkstra(start, distance):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0

    while q:
        dist, now = heapq.heappop(q)
        if distance[now] < dist: continue

        for _node, _dist in graph[now]:
            d = dist + _dist
            if d < distance[_node]:
                distance[_node] = d
                heapq.heappush(q, (d, _node))

dijkstra(A, distance_a)
dijkstra(B, distance_b)
dijkstra(C, distance_c)

max_distance = 0
answer = INF

for node in range(1, N + 1):
    if node in (A, B, C): continue
    distance = min(distance_a[node], distance_b[node], distance_c[node])
    if max_distance < distance:
        answer = node
        max_distance = distance
    elif max_distance == distance:
        answer = min(answer, node)

print(answer)
# 유니온파인드(크루스칼)

N, M = map(int, input().split())
school = [""] + input().split()

costs = []
for _ in range(M):
    u, v, d = map(int, input().split())
    if school[u] != school[v]: costs.append((d, u, v))
costs.sort()

parent = [0] * (N+1)
for i in range(1, N+1):
    parent[i] = i

def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]

def union(a, b):
    a = find(a)
    b = find(b)
    if a > b: parent[b] = a
    else: parent[a] = b

answer = 0
visited = [0] * (N+1)
for d, u, v in costs:
    if find(u) != find(v):
        union(u, v)
        visited[u] = 1
        visited[v] = 1
        answer += d

print(answer if sum(visited) == N else -1)
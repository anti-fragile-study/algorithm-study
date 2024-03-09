from collections import deque

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, N+1):
    graph[i].sort()
    
S, E = map(int, input().split())
visited = [False] * (N+1)

answer = 0
# S -> E
q = deque([(S, [S])])
visited[S] = True
while q:
    now, path = q.popleft()
    if now == E:
        answer += len(path) - 1
        break

    for nxt in graph[now]:
        if not visited[nxt]:
            visited[nxt] = True
            q.append((nxt, path + [nxt]))

# E -> S
visited = [False] * (N+1)
for node in path:
    visited[node] = True
visited[S] = False

q = deque([(E, 0)])
while q:
    now, dist = q.popleft()
    if now == S:
        answer += dist
        break
    for nxt in graph[now]:
        if not visited[nxt]:
            visited[nxt] = True
            q.append((nxt, dist + 1))


print(answer)

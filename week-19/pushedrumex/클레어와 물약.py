from collections import deque

N, M = map(int, input().split())
indegree = {}
graph = [[] for _ in range(N+1)]

for _ in range(M):
    arr = list(map(int, input().split()))
    xt = tuple(arr[1:])
    for x in arr[1:-1]:
        graph[x].append(xt)

    indegree[xt] = arr[0]

L = int(input())
q = deque(map(int, input().split()))
visited = [False] * (N+1)
for l in q:
    visited[l] = True

answer = []
while q:
    l = q.popleft()
    answer.append(l)

    for xt in graph[l]:
        indegree[xt] -= 1
        if indegree[xt] == 0 and not visited[xt[-1]]:
            visited[xt[-1]] = True
            q.append(xt[-1])

answer = sorted(set(answer))
print(len(answer))
print(*answer)

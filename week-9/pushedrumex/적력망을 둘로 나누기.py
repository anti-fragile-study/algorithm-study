from collections import deque

def solution(n, wires):
    answer = int(1e9)
    visited = [False] * (n + 1)
    graph = [[] for _ in range(n + 1)]
    for a, b in wires:
        graph[a].append(b)
        graph[b].append(a)
        
    for a, b in wires:
        visited[a], visited[b] = True, True
        temp = 1
        q = deque([a])
        while q:
            now = q.popleft()
            for node in graph[now]:
                if not visited[node]:
                    temp += 1
                    visited[node] = True
                    q.append(node)
        answer = min(answer, abs(temp - (n-temp)))
        visited = [False] * (n + 1)
    return answer
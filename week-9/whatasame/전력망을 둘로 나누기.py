from collections import defaultdict


def solution(n, wires):
    answer = float("inf")

    # 무방향 그래프 생성
    graph = defaultdict(set)
    for u, v in wires:
        graph[u].add(v)
        graph[v].add(u)

    disconnected = defaultdict(set)
    for u, v in wires:
        visited = [False for _ in range(n + 1)]

        disconnected[u].add(v)
        count_u = dfs(graph, u, visited, disconnected)
        count_v = dfs(graph, v, visited, disconnected)
        disconnected[u].remove(v)

        answer = min(answer, abs(count_u - count_v))

    return answer


def dfs(graph, start, visited, disconnected):
    count = 1
    visited[start] = True

    for neighbor in graph[start]:
        if visited[neighbor] or neighbor in disconnected[start]:
            continue
        count += dfs(graph, neighbor, visited, disconnected)

    return count

from collections import deque
from collections import defaultdict


def solution(n, edge):
    answer = 0

    visited = [False] * (n + 1)
    queue = deque()
    graph = defaultdict(list)

    for p1, p2 in edge:
        graph[p1].append(p2)
        graph[p2].append(p1)

    current_max = 0

    queue.append((1, 0))
    visited[1] = True

    while queue:
        node, cost = queue.popleft()

        if cost > current_max:
            answer = 1
            current_max = cost
        elif cost == current_max:
            answer += 1

        for next in graph[node]:
            if not visited[next]:
                queue.append((next, cost + 1))
                visited[next] = True

    return answer

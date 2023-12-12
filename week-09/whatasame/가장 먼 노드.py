# 무방향 그래프
# 노드 번호: 1 ~ n

"""
[[1, 2], [2, 3], [3, 4], [4, 5]]
-> 1
"""

from collections import defaultdict, deque


def solution(n, edges):
    # 무방향 그래프 선언
    graph = defaultdict(list)
    for u, v in edges:
        graph[u].append(v)
        graph[v].append(u)

    # BFS로 거리 업데이트
    costs = [-1 for _ in range(n + 1)]  # 0 ~ n

    q = deque()
    q.append((1, 0))  # node, cost
    costs[1] = 0

    while q:
        node, cost = q.popleft()

        for neighbor in graph[node]:
            # 방문 했다면 무시
            if costs[neighbor] != -1:
                continue

            q.append((neighbor, cost + 1))
            costs[neighbor] = cost + 1

    # 거리 중에 가장 멀리 떨어진 노드들 반환
    max_cost = max(costs)

    answer = list(filter(lambda cost: cost == max_cost, costs))

    return len(answer)

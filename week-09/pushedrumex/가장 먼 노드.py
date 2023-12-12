import heapq

def solution(n, edge):
    INF = int(1e9)
    distance = [INF] * (n+1)
    graph = [[] for _ in range(n+1)]
    for a, b in edge:
        graph[a].append(b)
        graph[b].append(a)
    q = []
    heapq.heappush(q, (0, 1))
    distance[1] = 0
    while q:
        cost, now = heapq.heappop(q)
        if distance[now] < cost: continue
        for node in graph[now]:
            _cost = cost + 1
            if distance[node] > _cost:
                distance[node] = _cost
                heapq.heappush(q, (_cost, node))

    max_cost = 0
    answer = 0
    for cost in distance[1:]:
        if max_cost < cost:
            answer = 1
            max_cost = cost
        elif max_cost == cost:
            answer += 1
    
    return answer
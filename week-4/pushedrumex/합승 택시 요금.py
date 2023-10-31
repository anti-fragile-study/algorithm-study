# S 에서 모든 지점까지의 최단 거리
# A, B 에서 모든 지점까지의 최단 거리
# 1 ~ N 점을 경유해서 가는 방법 중 최소 요금
import heapq

INF = int(1e9)
graph = None
answer = INF
def solution(n, s, a, b, fares):
    global graph
    
    graph = [[] for _ in range(n+1)]
    for p1, p2, d in fares:
        graph[p1].append((d, p2))
        graph[p2].append((d, p1))
    
    distance_s = [INF] * (n+1)
    distance_a = [INF] * (n+1)
    distance_b = [INF] * (n+1)
    
    dijkstra(s, distance_s)
    dijkstra(a, distance_a)
    dijkstra(b, distance_b)
    
    answer = INF
    for node in range(1, n+1):
        answer = min(answer, distance_s[node]+distance_a[node]+distance_b[node])

    return answer

def dijkstra(start, distance):
    q = []
    distance[start] = 0
    heapq.heappush(q, (0, start))
    
    while q:
        dist, node = heapq.heappop(q)
        if distance[node] < dist: continue
        
        for _dist, _node in graph[node]:
            d = _dist + dist
            if distance[_node] > d:
                distance[_node] = d
                heapq.heappush(q, (d, _node))
            
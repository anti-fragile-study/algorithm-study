# S -> E, E -> S 최단 거리를 구해 합을 반환
# E -> S는 S -> E의 정점과 중복되면 안됨
# 최단 경로가 여러개 존재하는 경우 사전 순으로 빠른 것을 선택

# 양방향 그래프
# 거리는 1

import sys
from collections import defaultdict
from heapq import *

input = sys.stdin.readline
n, m = map(int, input().split())
graph = defaultdict(list)
for _ in range(m):
    u, v = map(int, input().split())
    graph[u].append((v, 1))
    graph[v].append((u, 1))
for key, val in graph.items():
    graph[key] = sorted(val)
s, e = map(int, input().split())

def solution(graph, s, e):
    def dijkstra(start, excludes):
        distances = [987654321 for _ in range(n + 1)]
        paths = [[] for _ in range(n + 1)]
        min_heap = [] # (dist, node, path)

        distances[start] = 0
        paths[start] = [start]
        heappush(min_heap, (0, start, paths[start]))

        while min_heap:
            dist, node, path = heappop(min_heap)

            if dist > distances[node] or node in excludes:
                continue

            for neighbor, weight in graph[node]:
                new_dist = dist + weight

                if new_dist < distances[neighbor]:
                    distances[neighbor] = new_dist
                    paths[neighbor] = path + [neighbor]
                    heappush(min_heap, (new_dist, neighbor, paths[neighbor]))

        return distances, paths

    s_distances, s_paths = dijkstra(s, [])
    e_distances, e_paths = dijkstra(e, s_paths[e][1:-1])

    return s_distances[e] + e_distances[s]

print(solution(graph, s, e))

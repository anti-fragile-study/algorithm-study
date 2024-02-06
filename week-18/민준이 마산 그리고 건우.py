# 건우를 도와주는 경로가 최단 경로보다 긴지 아닌지 판단

import sys
from heapq import *
from collections import *

input = sys.stdin.readline
v, e, p = map(int, input().split())
graph = defaultdict(list)
for _ in range(e):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

def solution(graph):
    def dijkstra(start, end):
        dp = [987654321 for _ in range(v + 1)]
        dp[start] = 0

        min_heap = [] # (dist, node)
        heappush(min_heap, (0, start))
        while min_heap:
            dist, node = heappop(min_heap)
            if dist > dp[node]:
                continue

            for neighbor_node, neighbor_dist in graph[node]:
                new_dist = dist + neighbor_dist
                if new_dist < dp[neighbor_node]:
                    dp[neighbor_node] = new_dist
                    heappush(min_heap, (new_dist, neighbor_node))

        return dp

    # 1번부터 v번까지 최단 경로를 구한다.
    path = dijkstra(1, v)

    # 1~p 최단 경로, p~v 최단 경로를 구한다.
    # 1~v, 1~p + p~v 중 어느 것이 긴 지 판단한다.
    if path[v] < path[p] + dijkstra(p, v)[v]:
        return "GOOD BYE"
    return "SAVE HIM"

print(solution(graph))

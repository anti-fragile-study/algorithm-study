from collections import defaultdict
import heapq

def solution(n, paths, gates, summits):
    summit_dic = defaultdict(bool)
    gate_dic = defaultdict(bool)
    
    for summit in summits: summit_dic[summit] = True
    for gate in gates: gate_dic[gate] = True
    
    graph = [[] for _ in range(n + 1)]
    for a,b,d in paths:
        graph[a].append([b, d])
        graph[b].append([a, d])
        
    q = []
    intensity = [int(1e9)] * (n+1)
    for gate in gates:
        heapq.heappush(q, [0, gate])
        intensity[gate] = 0
    
    while q:
        dist, now = heapq.heappop(q)
        if intensity[now] < dist: continue
        for node, d in graph[now]:
            if gate_dic[node]: continue
            d = max(d, dist)
            if intensity[node] > d:
                intensity[node] = d
                if summit_dic[node]: continue
                heapq.heappush(q, [d, node])
                
    answer = [0, int(1e9)]
    for summit in sorted(summits):
        if intensity[summit] < answer[1]:
            answer = [summit, intensity[summit]]
    return answer
    
# 도넛 모양 : 정점 개수 == 간선 개수
# 막대 모양 : 정점 개수 == 간선개수 + 1
# 8자 모양 : 정점 개수 == 간선개수 - 1
# 나가는 간선 개수가 2개 이상이거나 들어오는 간선이 없으면 생성한 정점
from collections import defaultdict, deque

graph = defaultdict(list)
def solution(edges):
    get_edge = defaultdict(bool)
    도넛, 막대, 팔자 = 0, 0, 0
    
    for a, b in edges:
        graph[a].append(b)
        get_edge[b] = True
    
    # 생성한 정점 찾기
    for node in graph.keys():
        if not get_edge[node] and len(graph[node]) >= 2:
            new_node = node
            break
            
        if not get_edge[node]:
            new_node = node
    
    for node in graph[new_node]:
        edge, node = count(node, 0, 1)
        if node == edge:
            도넛 += 1
        elif node == edge + 1:
            막대 += 1
        elif node == edge - 1:
            팔자 += 1

    return [new_node, 도넛, 막대, 팔자]

def count(now, edge, node):
    q = deque([now])
    visited_node = defaultdict(bool)
    visited_edge = defaultdict(bool)
    visited_node[now] = True
    
    while q:
        now = q.pop()
        for nxt in graph[now]:
            if not visited_node[nxt]:
                visited_node[nxt] = True
                node += 1
                q.append(nxt)
            if not visited_edge[(now, nxt)]:
                visited_edge[(now, nxt)] = True
                edge += 1
                q.append(nxt)
    return (edge, node)
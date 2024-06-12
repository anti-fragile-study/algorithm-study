# 단방향 그래프
# 도넛: n개의 정점, n개의 간선 -> 전체가 하나의 사이클인 그래프
# 막대: n개의 정점, n - 1개의 간선 -> 쭉 이어지는 그래프
# 8자: 2n + 1개의 정점, 2n + 2개의 간선 -> 두 도넛의 한 정점을 겹쳐 만든 것

# 도넛, 막대, 8자 그래프가 여러개 있었다. 여기에 정점을 하나 추가한 후 각 그래프와 이었다.
# 이후 정점에 번호를 매겼다.

# 추가된 정점 번호와 정점이 추가되기 전 도넛, 막대, 8자 그래프의 개수를 반환

"""
[[4, 5], [4, 3], [3, 2], [2, 1]]
-> [4, 0, 2, 0]

[[4, 5], [4, 2], [3, 2], [2, 1]]
-> [4, 0, 2, 0]

[[4, 5], [4, 1], [3, 2], [2, 1]]
-> [4, 0, 2, 0]

[[4, 5], [4, 3], [3, 2], [2, 1], [1, 3]]
-> [4, 1, 1, 0]

[[4, 5], [4, 3], [3, 2], [2, 1], [1, 2], [2, 3]]
-> [4, 0, 1, 1]

[[5, 5], [4, 5], [4, 2], [3, 2], [2, 1], [1, 2], [2, 3]]
-> [4, 1, 0, 1]
"""

from collections import *

def solution(edges):
    # 도넛: 모든 정점이 out-degree와 in-degree가 1
    # 막대: out-degree가 0인 정점과 in-degree가 0인 정점이 1개씩 존재 (같을 수도 있음)
    # 8자: out-degree가 2이고 in-degree가 2인 정점이 1개 존재
    # 새로운 정점: out-degree가 2 이상(3종 그래프의 수)이고 in-degree가 0

    graph = defaultdict(lambda: [0, 0]) # out-degree, in-degree
    for u, v in edges:
        graph[u][0] += 1
        graph[v][1] += 1

    vertex = 0
    loop, bar_start, bar_end = 0, 0, 0
    for k, (out_degree, in_degree) in graph.items():
        if out_degree >= 2 and in_degree == 0: # 새로운 정점
            vertex = k
        elif out_degree == 2 and in_degree >= 2: # 8자
            loop += 1
        elif in_degree == 0: # 막대 시작
            bar_start += 1
        elif out_degree == 0: # 막대 끝
            bar_end += 1

    bar = max(bar_start, bar_end)
    donut = graph[vertex][0] - bar - loop

    return [vertex, donut, bar, loop]

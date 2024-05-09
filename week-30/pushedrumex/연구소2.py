from collections import deque
import copy
from itertools import combinations

N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]

def count_blank(graph):
    blank = 0
    for i in range(N):
        for j in range(N):
            if graph[i][j] == 0:
                blank += 1
    return blank

positions = []
for i in range(N):
    for j in range(N):
        if graph[i][j] == 2:
            positions.append((i, j))
            graph[i][j] = 0
        elif graph[i][j] == 1:
            graph[i][j] = "-"

INF = int(1e9)
result = INF
dxdy = ((1,0),(-1,0),(0,1),(0,-1))
for p in combinations(positions, M):
    _graph = copy.deepcopy(graph)
    time = 0
    for x, y in p:
        _graph[x][y] = 1

    q = deque(p)
    while q:
        x, y = q.popleft()
        for dx, dy in dxdy:
            _x = x + dx
            _y = y + dy
            if not (0 <= _x < N and 0 <= _y < N) or _graph[_x][_y] != 0:
                continue
            time = _graph[x][y]
            _graph[_x][_y] = _graph[x][y] + 1
            q.append((_x, _y))

    if count_blank(_graph) == 0:
        result = min(result, time)

print(result if result != INF else -1)
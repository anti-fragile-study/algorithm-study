from collections import deque
from collections import defaultdict

dxdy1 = ((0, 1), (1, 0), (-1, 0), (0, -1))
line = defaultdict(bool)
def solution(rectangle, characterX, characterY, itemX, itemY):
    graph = [[0] * 51 for _ in range(51)]
    
    for x1, y1, x2, y2 in rectangle:
        for x in range(x1, x2+1):
            for y in range(y1, y2+1):
                graph[y][x] = 1

    for x in range(1, 51):
        for y in range(1, 51):
            if graph[y][x] == 1:
                for dx, dy in dxdy1:
                    _x, _y = x + dx, y + dy
                    if not (1 <= _x < 51 and 1 <= _y < 51): continue
                    if graph[_y][_x] == 1:
                        line[(_x, _y, x, y)] = True
                        line[(x, y, _x, _y)] = True
                
    visited = [[False] * 51 for _ in range(51)]
    visited[characterY][characterX] = True
    q = deque([(characterX, characterY, 0)])
    while q:
        x, y, dist = q.popleft()
        if (x, y) == (itemX, itemY):
            return dist
        
        for dx, dy in dxdy1:
            _x, _y = x + dx, y + dy
            if not (1 <= _x < 51 and 1 <= _y < 51) or visited[_y][_x]: continue
            if graph[_y][_x] == 0: continue
            if is_boundary(graph, _x, _y):
                visited[_y][_x] = True
                q.append((_x, _y, dist + 1))

dxdy2 = ((0, 1), (1, 0), (-1, 0), (0, -1), (1, 1), (-1, 1), (1, -1), (-1, -1))
def is_boundary(graph, x, y):
    if x == 1 or y == 1 or x == 50 or y == 50: return True
    return  not (line[(x-1, y+1, x, y+1)] and \
            line[(x, y+1, x+1, y+1)] and \
            line[(x+1, y+1, x+1, y)] and \
            line[(x+1, y, x+1, y-1)] and \
            line[(x+1, y-1, x, y-1)] and \
            line[(x, y-1, x-1, y-1)] and \
            line[(x-1, y-1, x-1, y)] and \
            line[(x-1, y, x-1, y+1)])
from itertools import combinations
from collections import deque

INF = int(1e9)
N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

virus = []
for i in range(N):
    for j in range(N):
        if arr[i][j] == 2:
            virus.append((i, j))


def is_spread():
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 1: continue
            if _arr[i][j] == -1: return False
    return True

def getTime():
    time = 0
    for i in range(N):
        for j in range(N):
            if arr[i][j] == 1: continue
            time = max(time, _arr[i][j])
    return time

answer = INF
dxdy = ((1,0),(-1,0),(0,1),(0,-1))
for positions in combinations(virus, M):
    _arr = [[-1] * N for _ in range(N)]
    q = deque()
    for position in positions:
        q.append(position)
        _arr[position[0]][position[1]] = 0
    
    while q:
        x, y = q.popleft()
        for dx, dy in dxdy:
            _x, _y = x+dx, y+dy
            if not (0 <= _x < N and 0 <= _y < N): continue
            if arr[_x][_y] == 1 or _arr[_x][_y] > -1: continue
            _arr[_x][_y] = _arr[x][y] + 1
            q.append((_x, _y))

    if is_spread():
        answer = min(answer, getTime())

print(answer if answer != INF else -1)

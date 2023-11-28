import heapq

def solution(board):
    N = len(board)
    INF = int(1e9)
    distance = [[[INF] * 4 for _ in range(N)] for _ in range(N)]
    for i in range(4): distance[0][0][i] = 0
    graph = [[[] for _ in range(N)] for _ in range(N)]
    
    dxdy = ((-1,0), (0,1),(1,0),(0,-1))
    for i in range(N):
        for j in range(N):
            for dx, dy in dxdy:
                if board[i][j] == 1: continue
                _i, _j = i + dx, j + dy
                if not (0 <= _i < N and 0 <= _j < N) or board[_i][_j] == 1: continue
                graph[i][j].append((_i, _j))
    
    q = []
    direc = {(-1,0):0, (0,1):1, (1,0):2, (0,-1):3}
    if board[0][1] == 0:
        heapq.heappush(q, (100, 0, 1, direc[(0, 1)]))
        distance[0][1][1] = 100
    if board[1][0] == 0:
        heapq.heappush(q, (100, 1, 0, direc[(1, 0)]))
        distance[1][0][3] = 100
    
    while q:
        cost, x, y, d = heapq.heappop(q)
        if distance[x][y][d] < cost: continue
        for _x, _y in graph[x][y]:
            _d = direc[(_x - x, _y - y)]
            _cost = cost + 100
            if _d != d: _cost += 500
            if distance[_x][_y][_d] >= _cost:
                distance[_x][_y][_d] = _cost
                heapq.heappush(q, (_cost, _x, _y, _d))
        
    return min(distance[N-1][N-1])
    
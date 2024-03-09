from collections import deque

dxdy = ((0,1),(1,0),(-1,0),(0,-1))
def bfs(ground, x, y):
    q = deque([(x, y)])
    ground[x][y] = 0
    while q:
        x, y = q.popleft()
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if not (0 <= _x < N and 0 <= _y < M) or ground[_x][_y] == 0: continue
            ground[_x][_y] = 0
            q.append((_x, _y))

T = int(input())
for _ in range(T):
    M, N, K = map(int, input().split())
    ground = [[0] * M for _ in range(N)]

    for _ in range(K):
        y, x = map(int, input().split())
        ground[x][y] = 1
    
    answer = 0
    for i in range(N):
        for j in range(M):
            if ground[i][j] == 1:
                bfs(ground, i, j)
                answer += 1

    print(answer)
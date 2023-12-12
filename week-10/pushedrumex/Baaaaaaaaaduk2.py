from collections import deque

# 그룹이 상대방의 바둑 + 벽으로만 둘러싸여있다면 먹힌것
N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(N)]

dxdy = ((1, 0), (-1, 0), (0, -1), (0, 1))
def bfs(start):
    visited[start[0]][start[1]] = True
    flag = False
    q = deque([start])
    count = 1
    while q:
        x, y = q.pop()
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if not (0 <= _x < N and 0 <= _y < M) or graph[_x][_y] == 1 or visited[_x][_y]: continue
            if graph[_x][_y] == 0:
                flag = True
            elif graph[_x][_y] == 2:
                visited[_x][_y] = True
                if not flag: count += 1
                q.appendleft((_x, _y))
                
    return 0 if flag else count

answer = 0
for x1 in range(N):
    for y1 in range(M):
        if graph[x1][y1] != 0: continue
        for x2 in range(x1, N):
            for y2 in range(M):
                if graph[x2][y2] != 0: continue
                if (x1, y1) == (x2, y2): continue
                graph[x1][y1] = 1
                graph[x2][y2] = 1
                
                visited = [[False] * M for _ in range(N)]
                temp = 0
                for i in range(N):
                    for j in range(M):
                        if graph[i][j] == 2 and not visited[i][j]:
                            temp += bfs((i, j))

                answer = max(answer, temp)
                graph[x1][y1] = 0
                graph[x2][y2] = 0
print(answer)
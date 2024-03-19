from collections import deque

N = int(input())
일정 = [list(map(int, input().split())) for _ in range(N)]
일정.sort(key=lambda x: (x[0], -x[1]))

달력 = [[False] * 366 for _ in range(N)]
for 시작, 종료 in 일정:
    i = 0
    while 달력[i][시작]:
        i += 1
    
    for j in range(시작, 종료+1):
        달력[i][j] = True

dxdy = ((1,0),(-1,0),(0,1),(0,-1))
def bfs(x, y):
    q = deque([(x, y)])
    end_x, end_y = x, y

    while q:
        x, y = q.popleft()
        end_x, end_y = max(end_x, x), max(end_y, y)
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if not (0 <= _x < N and 0 <= _y < 366):
                continue
            if 달력[_x][_y] == False:
                continue
            달력[_x][_y] = False
            q.append((_x, _y))

        _x = -1
        _y = y+1
        while _x < N:
            _x += 1
            if not (0 <= _x < N and 0 <= _y < 366):
                continue
            if 달력[_x][_y] == False:
                continue
            달력[_x][_y] = False
            q.append((_x, _y))
    return (end_x, end_y)

answer = 0
for i in range(N):
    for j in range(366):
        if 달력[i][j] == True:
            달력[i][j] = False
            _i, _j = bfs(i, j)
            answer += (_i-i+1) * (_j-j+1)

print(answer)
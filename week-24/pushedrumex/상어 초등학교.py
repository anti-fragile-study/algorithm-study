N = int(input())
graph = [[0] * N for _ in range(N)]
likes = {}
dxdy = [[1,0],[0,1],[-1,0],[0,-1]]

def get_blank():
    for i in range(N):
        for j in range(N):
            if graph[i][j] == 0:
                return (i, j)

for _ in range(N**2):
    a, b1, b2, b3, b4 = map(int, input().split())
    likes[a] = [b1, b2, b3, b4]

    x, y = get_blank()

    like, blank = 0,0
    for i in range(N):
        for j in range(N):
            if graph[i][j] != 0: continue
            _blank, _like = 0, 0
            for dx, dy in dxdy:
                _i, _j = i + dx, j + dy
                if not (0 <= _i < N and 0 <= _j < N): continue
                if graph[_i][_j] == 0: _blank += 1
                elif graph[_i][_j] in likes[a]: _like += 1

            if like < _like:
                x, y = i, j
                like, blank = _like, _blank
            elif _like == like and blank < _blank:
                x, y = i, j
                blank = _blank
                
    graph[x][y] = a

answer = 0
score = {0:0, 1:1, 2:10, 3:100, 4:1000}
for i in range(N):
    for j in range(N):
        a = graph[i][j]
        count = 0
        for dx, dy in dxdy:
            _i, _j = i + dx, j + dy
            if not (0 <= _i < N and 0 <= _j < N): continue
            if graph[_i][_j] in likes[a]:
                count += 1
        answer += score[count]

print(answer)

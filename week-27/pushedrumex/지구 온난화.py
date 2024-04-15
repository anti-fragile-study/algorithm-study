R, C = map(int, input().split())
graph = [list(input()) for _ in range(R)]
after = [["."] * C for _ in range(R)]

dxdy = ((1, 0), (-1, 0), (0, 1), (0, -1))
for x in range(R):
    for y in range(C):
        if graph[x][y] == ".":
            continue
        sea = 0
        for dx, dy in dxdy:
            _x = x + dx
            _y = y + dy
            if not (0 <= _x < R and 0 <= _y < C):
                sea += 1
                continue
            if graph[_x][_y] == ".":
                sea += 1
        if sea < 3:
            after[x][y] = "X"

x1, y1 = R-1, C-1
x2, y2 = 0, 0

for x in range(R):
    for y in range(C):
        if after[x][y] == "X":
            x1 = min(x1, x)
            y1 = min(y1, y)
            x2 = max(x2, x)
            y2 = max(y2, y)

for x in range(x1, x2+1):
    print("".join(after[x][y1:y2+1]))
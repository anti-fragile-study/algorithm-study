from collections import deque

SIZE = 101
dxdy = ((0,-1), (0,1), (1,0), (-1,0))
def solution(rectangle, characterX, characterY, itemX, itemY):
    outer = [[0] * SIZE for _ in range(SIZE)]
    inner = [[0] * SIZE for _ in range(SIZE)]

    for x1, y1, x2, y2 in rectangle:

        x1 *= 2
        y1 *= 2
        x2 *= 2
        y2 *= 2

        # outer
        for y in (y1, y2):
            for x in range(x1, x2+1):
                if inner[x][y] == 0:
                    outer[x][y] = 1
        for x in (x1, x2):
            for y in range(y1, y2+1):
                if inner[x][y] == 0:
                    outer[x][y] = 1

        # inner
        for x in range(x1+1, x2):
            for y in range(y1+1, y2):
                outer[x][y] = 0
                inner[x][y] = 1

    q = deque([(characterX*2, characterY*2, 0)])
    outer[characterX*2][characterY*2] = 0
    while q:
        x, y, d = q.popleft()
        for dx, dy in dxdy:
            _x = x + dx
            _y = y + dy
            if not (0 <= _x < SIZE and 0 <= _y < SIZE): continue
            if outer[_x][_y] == 0: continue
            if (_x, _y) == (itemX*2, itemY*2):
                return (d + 1) // 2

            outer[_x][_y] = 0
            q.append((_x, _y, d+1))
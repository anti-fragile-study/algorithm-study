park, N, M = None, None, None
def solution(_park, routes):
    global park, N, M
    
    park = _park
    N, M = len(park), len(park[0])
    
    for i in range(N):
        for j in range(M):
            if park[i][j] == "S":
                x, y = i, j

    for route in routes:
        d, s = route.split()
        s = int(s)
        temp = go(x, y, d, s)
        if temp != None:
            x, y = temp
        
    return [x, y]

def go(x, y, d, s):
    if d == "E":
        dx, dy = 0, 1
    elif d == "W":
        dx, dy = 0, -1
    elif d == "S":
        dx, dy = 1, 0
    else:
        dx, dy = -1, 0
    
    for _ in range(s):
        x += dx
        y += dy
        if not (0 <= x < N and 0 <= y < M) or park[x][y] == "X":
            return None
        
    return (x, y)
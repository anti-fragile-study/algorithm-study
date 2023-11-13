from collections import deque

def solution(places):
    answer = []
    for place in places:
        answer.append(is_possible(place))
    return answer

def is_possible(place):
    for i in range(5):
        for j in range(5):
            if place[i][j] == "P":
                temp = bfs(i, j, place)
                if temp == 0: return 0
    return 1

dxdy = ((0,1),(1,0),(-1,0),(0,-1))
def bfs(start_x, start_y, place):
    visited = [[False] * 5 for _ in range(5)]
    visited[start_x][start_y] = True
    q = deque([(start_x, start_y)])
    while q:
        x, y = q.popleft()
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if not (0 <= _x < 5 and 0 <= _y < 5) or visited[_x][_y]: continue
            if abs(start_x - _x) + abs(start_y - _y) > 2: continue
            if place[_x][_y] == "X": continue
            if place[_x][_y] == "P": return 0
            visited[_x][_y] = True
            q.append([_x, _y])
    return 1
from collections import deque, defaultdict

# 상하좌우
dxdy = ((0,1),(1,0),(0,-1),(-1,0))
# 90도 회전(가로)
dhr = (((0, 0, -1, 0), (0, 0, -1, -1)), ((0, 0, 1, 0), (0, 0, 1, -1)), ((-1, 0, 0, 0), (-1, 1, 0, 0)), ((1, 0, 0, 0), (1, 1, 0, 0)))
# 90도 회전(세로)
dvr = (((0, 0, 0, -1), (0, 0, -1, -1)), ((0, 0, 0, 1), (0, 0, -1, 1)), ((0, -1, 0, 0), (1, -1, 0, 0)), ((0, 1, 0, 0), (1, 1, 0, 0)))

N = None
visited = defaultdict(bool)
def solution(board):
    global N
    N = len(board)
    q = deque([(0, 0, 0, 1, 0)])
    while q:
        x1, y1, x2, y2, cnt = q.popleft()
        if (x1, y1) == (N-1, N-1) or (x2, y2) == (N-1, N-1): return cnt
        for dx, dy in dxdy:
            _x1, _y1, _x2, _y2 = x1+dx, y1+dy, x2+dx, y2+dy
            if ofb(_x1, _y1) or ofb(_x2, _y2): continue
            if visited[(_x1, _y1, _x2, _y2)]: continue
            if board[_x1][_y1] + board[_x2][_y2] > 0: continue
            q.append((_x1, _y1, _x2, _y2, cnt + 1))
            visited[(_x1, _y1, _x2, _y2)] = True
            visited[(_x2, _y2, _x1, _y1)] = True
            
        direc = (abs(x1-x2), abs(y1-y2))
        if (0, 1) == direc:
            dr = dhr
            if y1 > y2: y1, y2 = y2, y1
        else:
            dr = dvr
            if x1 > x2: x1, x2 = x2, x1
        for path in dr:
            if rotate_possible(x1, y1, x2, y2, path, board):
                _x1, _y1, _x2, _y2 = x1 + path[-1][0], y1 + path[-1][1], x2 + path[-1][2], y2 + path[-1][3]
                if visited[(_x1, _y1, _x2, _y2)]: continue
                q.append((_x1, _y1, _x2, _y2, cnt + 1))
                visited[(_x1, _y1, _x2, _y2)] = True
                visited[(_x2, _y2, _x1, _y1)] = True
                
def rotate_possible(x1, y1, x2, y2, path, board):
    for dx1, dy1, dx2, dy2 in path:
        _x1, _y1, _x2, _y2 = x1+dx1, y1+dy1, x2+dx2, y2+dy2
        if ofb(_x1, _y1) or ofb(_x2, _y2): return False
        if board[_x1][_y1] + board[_x2][_y2] > 0: return False
    return True

def ofb(x, y):
    return not (0 <= x < N and 0 <= y < N)
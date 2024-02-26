# 퍼즐 조각, 회전 후 퍼즐 조각, 방문 등을 모두 N by N 으로 저장 -> 시간초과
# 퍼즐 조각 점들을 리스트로 저장 -> 통과

from collections import deque
from copy import deepcopy

N = None
origin_game_board, game_board = None, None
table = None
dxdy = ((1, 0), (-1, 0), (0, -1), (0, 1))
def solution(_game_board, _table):
    global N, game_board, origin_game_board, table
    
    game_board = _game_board
    origin_game_board = deepcopy(_game_board)

    table = _table
    N = len(game_board)
    
    before_count = 0
    for row in game_board:
        before_count += sum(row)

    for i in range(N):
        for j in range(N):
            if table[i][j] == 1:
                points = get_points(i, j)
                _i, _j = i, j
                for _ in range(4):
                    points = rotate(points)
                    _i, _j = _j, N-_i-1

                    if is_possible(points):
                        break

    after_count = 0
    for row in game_board:
        after_count += sum(row)
        
    return after_count - before_count

def is_possible(points):
    for i in range(N):
        for j in range(N):
            if game_board[i][j] > 0: continue
            if not is_match(i, j, points): continue

            # game_board 에 넣기
            put(i, j, points)

            return True
        
    return False

def put(i, j, points):
    dx, dy = i - points[0][0], j - points[0][1]
    for x, y in points:
        game_board[x + dx][y + dy] = 1

def is_match(i, j, points):
    dx, dy = i - points[0][0], j - points[0][1]
    for x, y in points:
        x, y = x + dx, y + dy
        if ofb(x, y) or game_board[x][y] > 0: return False
    
        # 인접한 빈칸 유무 확인
        for dx1, dy1 in dxdy:
            _x, _y = x + dx1, y + dy1
            if ofb(_x, _y): continue
            if origin_game_board[_x][_y] == 0 and ((_x - dx, _y - dy) not in points): return False

    return True

def get_points(x, y):
    result = []
    q = deque([(x, y)])
    table[x][y] = 0
    result.append((x, y))
    while q:
        x, y = q.popleft()
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if ofb(_x, _y) or table[_x][_y] == 0: continue
            
            table[_x][_y] = 0
            result.append((_x, _y))
            q.append((_x, _y))
            
    return result

def rotate(points):
    result = []
    for x, y in points:
        result.append((y, N-x-1))
    return result 

def ofb(x, y):
    return not (0 <= x < N and 0 <= y < N)

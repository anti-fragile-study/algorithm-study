from collections import deque

N, M = None, None
game_board = None
table = None
dxdy = ((1, 0), (-1, 0), (0, -1), (0, 1))
def solution(_game_board, _table):
    global N, M, game_board, table
    
    game_board = _game_board
    table = _table
    N, M = len(game_board), len(game_board[0])
    
    answer = 0
    for i in range(N):
        for j in range(M):
            if table[i][j] == 1:
                piece = get_piece(i, j)
                for _ in range(4):
                    piece = rotate(piece)
                    i, j = j, N-i-1

                    if is_possible(i, j, piece):
                        temp = 0
                        for p in piece:
                            temp += sum(p)
                        answer += temp
                        break

    return answer

def is_possible(x, y, piece):
    for i in range(N):
        for j in range(M):
            if game_board[i][j] == 1: continue
            
            temp_board = [[0] * M for _ in range(N)]
            temp_board[i][j] = 1
            
            if not is_possible_insert(i, j, x, y, temp_board, piece): continue
            if not is_match(i, j, temp_board): continue
            
            # game_board 에 넣기
            for i in range(N):
                for j in range(M):
                    game_board[i][j] += temp_board[i][j]

            return True
        
    return False

def is_possible_insert(i, j, x, y, temp_board, piece):
    visited = [[False] * M for _ in range(N)]
    visited[x][y] = True

    q = deque([(x, y, i, j)])
    while q:
        x, y, i, j = q.popleft()
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if ofb(_x, _y) or piece[_x][_y] == 0 or visited[_x][_y]: continue

            _i, _j = i + dx, j + dy
            if ofb(_i, _j) or game_board[_i][_j] == 1: return False
            temp_board[_i][_j] = 1
            
            q.append((_x, _y, _i, _j))
            visited[_x][_y] = True

    return True

def is_match(i, j, temp_board):
    q = deque([(i, j)])
    visited = [[False] * M for _ in range(N)]
    visited[i][j] = True  
    while q:
        i, j = q.popleft()
        for dx, dy in dxdy:
            _i, _j = i + dx, j + dy
            if ofb(_i, _j) or visited[_i][_j]: continue
            
            if temp_board[_i][_j] == 1:
                q.append((_i, _j))
                visited[_i][_j] = True
                continue
                
            if game_board[_i][_j] == 0: return False

    return True

def get_piece(i, j):
    q = deque([(i, j)])
    table[i][j] = 0
    piece = [[0] * M for _ in range(N)]
    piece[i][j] = 1
    while q:
        x, y = q.popleft()
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if ofb(_x, _y) or table[_x][_y] == 0: continue
            table[_x][_y] = 0
            piece[_x][_y] = 1
            q.append((_x, _y))
            
    return piece
        
def ofb(x, y):
    return not (0 <= x < N and 0 <= y < M)
    
def rotate(piece):
    result = [[0] * M for _ in range(N)]
    for i in range(N):
        for j in range(M):
            result[j][N-i-1] = piece[i][j]
            
    return result

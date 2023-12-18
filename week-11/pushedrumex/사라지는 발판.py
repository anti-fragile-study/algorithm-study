answer = int(1e9)
def solution(board, aloc, bloc):
    play(board, aloc, bloc, 0)
    return answer

dxdy = ((0,1),(1,0),(-1,0),(0,-1))
def play(board, aloc, bloc, count):
    global answer
    ax, ay = aloc
    bx, by = bloc
    if board[ax][ay] == 0 or board[bx][by] == 0:
        answer = min(answer, count)

    flag = True
    if count % 2 == 0:
        for dx, dy in dxdy:
            _x = ax + dx
            _y = ay + dy
            if not (0 <= _x < len(board) and 0 <= _y < len(board[0])): continue
            if board[_x][_y] == 0: continue
            flag = False
            board[ax][ay] = 0
            play(board, (_x, _y), bloc, count + 1)
            board[ax][ay] = 1
    else:
        for dx, dy in dxdy:
            _x = bx + dx
            _y = by + dy
            if not (0 <= _x < len(board) and 0 <= _y < len(board[0])): continue
            if board[_x][_y] == 0: continue
            flag = False
            board[bx][by] = 0
            play(board, aloc, (_x, _y), count + 1)
            board[bx][by] = 1
    if flag:
        answer = min(answer, count)

def solution(_board, aloc, bloc):
    global board, M, N;
    board = _board
    M = len(_board)
    N = len(_board[0])
    
    return play(0, aloc, bloc)

dxdy = ((1,0),(-1,0),(0,1),(0,-1))
def play(count, player, rester):
    global answer
    
    x, y = player
    if board[x][y] == 0:
        return count
    
    win = []
    lose = []
    board[x][y] = 0
    move = False
    for dx, dy in dxdy:
        _x = x + dx
        _y = y + dy
        
        if oob(_x, _y): continue
        if board[_x][_y] == 0: continue
        move = True
        result = play(count + 1, rester, (_x, _y))
        
        if (count + result) % 2 == 0:
            lose.append(result)
        else:
            win.append(result)

    board[x][y] = 1
    
    if not move:
        return count

    if win:
        return min(win)
    
    return max(lose)
        
def oob(x, y):
    return not (0 <= x < M and 0 <= y < N)
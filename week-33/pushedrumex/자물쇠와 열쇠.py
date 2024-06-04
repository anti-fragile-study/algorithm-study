from copy import deepcopy

M, N = None, None
def solution(key, lock):
    global M, N
    
    M = len(key)
    N = len(lock)
    
    lock_board = [[0] * (3*N) for _ in range(3*N)]
    for i in range(N):
        for j in range(N):
            lock_board[i+N][j+N] = lock[i][j]
        
    for _ in range(4):
        key = rotate(key)
        if possible(key, lock_board):
            return True

    return False

def possible(key, lock_board):
    for ox in range(N-M, 2*N):
        for oy in range(N-M, 2*N):
            if match(deepcopy(lock_board), key, ox, oy):
                return True

    return False

def match(lock_board, key, ox, oy):
    for x in range(M):
        for y in range(M):
            lock_board[ox+x][oy+y] += key[x][y]
    
    for i in range(N, 2*N):
        for j in range(N, 2*N):
            if lock_board[i][j] != 1:
                return False

    return True
        
def rotate(key):
    new_key = [[0] * M for _ in range(M)]
    for x in range(M):
        for y in range(M):
            new_key[x][y] = key[y][M-x-1]
    return new_key
    
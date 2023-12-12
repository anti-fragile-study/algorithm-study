def solution(board, skill):
    M, N = len(board), len(board[0])
    answer = 0
    _board = [[0] * N for _ in range(M) ]
    
    for t, r1, c1, r2, c2, degree in skill:
        if t == 1: degree *= -1
        _board[r2][c2] += degree
        if c1 > 0: _board[r2][c1-1] += -degree
        if r1 > 0: _board[r1-1][c2] += -degree
        if r1 > 0 and c1 > 0: _board[r1-1][c1-1] += degree

    # 누적합
    for i in range(M):
        for j in range(N):
            if i > 0:
                _board[i][j] += _board[i-1][j]
            if j > 0:
                _board[i][j] += _board[i][j-1]
            if i > 0 and j > 0:
                _board[i][j] -= _board[i-1][j-1]
            
    for i in range(M):
        for j in range(N):
            board[i][j] += _board[M-1][N-1]
            if i > 0:
                board[i][j] -= _board[i-1][N-1]
            if j > 0:
                board[i][j] -= _board[M-1][j-1]
            if i > 0 and j > 0:
                board[i][j] += _board[i-1][j-1]
            if board[i][j] > 0: answer += 1
            
    return answer
def solution(n, results):
    answer = 0
    board = [[0]*(n+1) for _ in range(n+1)]
    
    for a, b in results:
        board[a][b] = 1
        board[b][a] = -1
        
    for k in range(1, n+1):
        for i in range(1, n+1):
            for j in range(1, n+1):
                if i == j or board[i][j] in [1,-1]:
                    continue
                if board[i][k] == board[k][j] == 1:
                    board[i][j] = 1
                    board[j][i] = board[k][i] = board[j][k] = -1

    for row in board:
        if row.count(0) == 2:
            answer += 1
    return answer
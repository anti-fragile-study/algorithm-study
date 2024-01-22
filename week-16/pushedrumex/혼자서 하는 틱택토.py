def solution(board):
    o_count, x_count = 0, 0
    for i in range(3):
        for j in range(3):
            if board[i][j] == "O":
                o_count += 1
            elif board[i][j] == "X":
                x_count += 1
    
    o_bingo, x_bingo = 0, 0
    for row in board:
        if row == "OOO":
            o_bingo += 1
        elif row == "XXX":
            x_bingo += 1
    
    for j in range(3):
        temp = ""
        for i in range(3):
            temp += board[i][j]

        if temp == "OOO":
            o_bingo += 1
        elif temp == "XXX":
            x_bingo += 1
            
    temp = board[0][0] + board[1][1] + board[2][2]
    if temp == "OOO":
        o_bingo += 1
    elif temp == "XXX":
        x_bingo += 1
    
    temp = board[0][-1] + board[1][-2] + board[2][-3]
    if temp == "OOO":
        o_bingo += 1
    elif temp == "XXX":
        x_bingo += 1
        
    if x_count > o_count: return 0
    if o_count - x_count > 1: return 0

    if o_bingo == 1 and x_count == o_count: return 0
    if x_bingo == 1 and x_count < o_count: return 0

    return 1
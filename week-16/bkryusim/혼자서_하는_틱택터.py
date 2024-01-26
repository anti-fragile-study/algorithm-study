def solution(board):
    o_count = 0
    x_count = 0

    for r in range(3):
        for c in range(3):
            if board[r][c] == 'O':
                o_count += 1
            if board[r][c] == 'X':
                x_count += 1

    if o_count - x_count != 0 and o_count - x_count != 1:
        return 0

    o_bingos = 0
    x_bingos = 0

    diagonals = [board[0][0] + board[1][1] + board[2][2], board[0][2] + board[1][1] + board[2][0]]

    for row in board + list(map(lambda x: ''.join(x), zip(*board[::-1]))) + diagonals:
        if row == "OOO":
            o_bingos += 1
        if row == "XXX":
            x_bingos += 1

    if o_bingos >= 1 and x_bingos >= 1:
        return 0

    if o_bingos >= 1 and o_count - x_count != 1:
        return 0

    if x_bingos >= 1 and o_count - x_count != 0:
        return 0
    return 1

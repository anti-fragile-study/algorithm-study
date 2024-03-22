def check_bingo(board, ch):
    # check rows
    for row in board:
        if all(val == ch for val in row):
            return True
        
    # check cols
    for c in range(len(board)):
        if all(board[r][c] == ch for r in range(len(board))):
            return True
        
    # check diag
    if all(board[r][r] == ch for r in range(len(board))):
        return True
    if all(board[r][len(board) - r - 1] == ch for r in range(len(board))):
        return True
    
    return False

def solution(board):
    X = 'X'
    O = 'O'
    
    x = 0
    o = 0
    
    # 개수 판단
    for row in board:
        for col in row:
            if col == X:
                x += 1
            elif col == O:
                o += 1

    if (o != x) and (o != x + 1):
        return 0
    
    # 빙고 판단
    if o == x and check_bingo(board, O):
        return 0
    if o == x + 1 and check_bingo(board, X):
        return 0
    
    return 1

def solution(board, skill):
    answer = len(board) * len(board[0])
    check = set([])
    
    for sk in skill:
        sk_type, r1, c1, r2, c2, degree = sk
        if sk_type == 1:
            for i in range(r1, r2 + 1):
                for j in range(c1, c2 + 1):
                    board[i][j] -= degree
                    check.add((i, j))

        else:
            for i in range(r1, r2 + 1):
                for j in range(c1, c2 + 1):
                    board[i][j] += degree
                    check.add((i, j))

    for ch in check:
        if board[ch[0]][ch[1]] <= 0:
            answer -= 1
    return answer

def solution(board, moves):
    stack = []
    answer = 0

    for col in moves:
        n = 0
        while n < len(board[0]) and board[n][col - 1] == 0:
            n += 1

        if n == len(board[0]):
            continue

        doll = board[n][col - 1]
        board[n][col - 1] = 0
        if stack and stack[-1] == doll:
            answer += 1
            stack.pop()
        else:
            stack.append(doll)

    return answer * 2

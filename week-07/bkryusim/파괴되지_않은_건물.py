def solution(board, skill):
    answer = 0
    behavior = [0, -1, 1]

    dp = [[0 for _ in range(len(board[0]) + 1)] for _ in range(len(board) + 1)]

    for type, r1, c1, r2, c2, degree in skill:
        dp[r1][c1] += behavior[type] * degree
        dp[r1][c2 + 1] -= behavior[type] * degree
        dp[r2 + 1][c1] -= behavior[type] * degree
        dp[r2 + 1][c2 + 1] += behavior[type] * degree

    # 가로방향 부분합
    for r in range(len(board)):
        for c in range(1, len(board[0])):
            dp[r][c] += dp[r][c - 1]

    # 세로방향 부분합
    for c in range(len(board[0])):
        for r in range(1, len(board)):
            dp[r][c] += dp[r - 1][c]

    # 변화율 매트릭스와 기존 board 부호 확인
    for r in range(len(board)):
        for c in range(len(board[0])):
            if board[r][c] + dp[r][c] > 0:
                answer += 1

    return answer

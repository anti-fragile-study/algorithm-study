def solution(game_board, table):
    answer = 0

    def neighbors(board, r, c):
        res = []

        if r > 0:
            res.append([r - 1, c])

        if r < len(board) - 1:
            res.append([r + 1, c])

        if c > 0:
            res.append([r, c - 1])

        if c < len(board[0]) - 1:
            res.append([r, c + 1])

        return res

    def dfs(board, color, r, c, shape):
        shape.append([r, c])
        board[r][c] = (color + 1) % 2

        for dr, dc in neighbors(board, r, c):
            if board[dr][dc] == color:
                dfs(board, color, dr, dc, shape)

        return shape

    def find_shapes(board, color):
        shapes = []

        for _ in range(len(board)):
            for j in range(len(board[0])):
                if board[i][j] != color:
                    continue
                shape = dfs(board, color, i, j, [])
                min_r = min(shape, key=lambda x: x[0])[0]
                min_c = min(shape, key=lambda x: x[1])[1]

                shape = set(map(lambda x: (x[0] - min_r, x[1] - min_c), shape))

                shapes.append(shape)
        return shapes

    def rotates(shape):
        res = []

        tmp = shape
        res.append(shape)

        for _ in range(3):
            tmp2 = set()
            max_tmp_r = max(tmp, key=lambda x: x[0])[0]
            for r, c in tmp:
                tmp2.add((c, max_tmp_r - r))

            res.append(tmp2)
            tmp = tmp2
        return res

    def is_matches(shape1, shape2):
        for r in rotates(shape2):
            if shape1 == r:
                return True, len(shape1)
        return False, 0

    board_shapes = find_shapes(game_board, 0)
    table_shapes = find_shapes(table, 1)

    for bs in board_shapes:
        for i, ts in enumerate(table_shapes):
            if ts is None:
                continue
            flag, cnt = is_matches(bs, ts)

            if flag:
                answer += cnt
                table_shapes[i] = None
                break

    return answer

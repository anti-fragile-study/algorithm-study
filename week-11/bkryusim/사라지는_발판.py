def solution(board, aloc, bloc):
    def neighbors(r, c):
        result = []
        if r > 0:
            result.append((r - 1, c))
        if r < len(board) - 1:
            result.append((r + 1, c))
        if c > 0:
            result.append((r, c - 1))
        if c < len(board[0]) - 1:
            result.append((r, c + 1))
        return list(filter(lambda x: board[x[0]][x[1]] == 1, result))

    # 미래의 승자, 게임 길이 체크
    def game(board, positions, count):
        player = count % 2
        opponent = (player + 1) % 2

        movables = neighbors(*positions[player])

        # 움직일 칸 없으면 상대가 승리
        if len(movables) == 0:
            return opponent, count

        # 이번에 움직이는데 적이 같은 칸에 있으면 한번 더 움직이고 내가 승리
        if positions[0] == positions[1]:
            return player, count + 1

        # 밟던 칸 바닥 없애기
        board[positions[player][0]][positions[player][1]] = 0

        result = [set(), set()]

        # 이동 가능 칸 순회하며 게임 기록
        for r, c in movables:
            new_positions = positions[:]
            new_positions[player] = (r, c)
            winner, length = game(board, new_positions, count + 1)
            result[winner].add(length)

        # 다시 복구
        board[positions[player][0]][positions[player][1]] = 1

        # 이길 수 있는 경우의 수가 있으면, 그 중 가장 짧은 미래 선택
        if result[player]:
            return player, min(result[player])

        # 이기는 미래가 없으면, 상대가 이기는 미래 중 가징 긴 것 선택
        return opponent, max(result[opponent])

    return game(board, [tuple(aloc), tuple(bloc)], 0)[1]

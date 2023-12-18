# 몇 번 움직이는지 예측
# 발판이 없는 곳: 0
# 발판이 있는 곳: 1
# 이동하면 기존의 공간은 발판 사라짐

# 이동
# 인접: 상하좌우
# 같은 위치로 이동가능

# 패배조건:
# 1. 더이상 못 움직이는 경우
# 2. 같이 있다가 상대방이 먼저 움직이는 경우

# 최적의 플레이
# 승자: 최대한 빨리 끝내기
# 패자: 최대한 늦게 끝내기

# 반환: 최적의 플레이를 했을 때 두 캐릭터가 움직인 횟수의 합

# A가 먼저 시작
# A, B의 시작 위치는 같을 수 있다.

# 앞으로 턴이 홀수번 남음 -> 내가 마지막으로 움직이고 끝남 -> 승리
# 앞으로 턴이 짝수번 남음 -> 상대가 마지막으로 움직이고 끝남 -> 패배

directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]


def solution(board, aloc, bloc):
    return turn(board, aloc, bloc)


def turn(board, aloc, bloc):
    ar, ac = aloc
    br, bc = bloc

    # 내 바닥이 와르르
    if board[ar][ac] == 0:
        return 0

    # 홀수 턴 남음: 승리 / 짝수 턴 남음: 패배
    now = 0  # 못 움직임 = 패배
    for dr, dc in directions:
        nar, nac = ar + dr, ac + dc

        if not movable(board, nar, nac):
            continue

        board[ar][ac] = 0  # 이동 시 와르르
        nxt = turn(board, (br, bc), (nar, nac)) + 1
        board[ar][ac] = 1

        if now % 2 == 0 and nxt % 2 == 0:  # 현재 패배인데 또 패배 -> 최대한 늦게 끝내기
            now = max(now, nxt)
        if now % 2 == 1 and nxt % 2 == 1:  # 현재 승리인데 또 승리 -> 최대한 빨리 끝내기
            now = min(now, nxt)
        if now % 2 == 0 and nxt % 2 == 1:  # 현재 패배인데 승리 -> 승리 선택
            now = nxt
        # 현재 승리인데 패배 -> 가만히 있으면 됨

    return now


def movable(board, r, c):
    return 0 <= r < len(board) and 0 <= c < len(board[0]) and board[r][c] == 1

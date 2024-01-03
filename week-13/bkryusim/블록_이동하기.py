from collections import deque


def peek(board, robot):
    result = []

    N = len(board)
    head = max(robot)
    tail = min(robot)

    # 상 이동
    if 0 < head[0] and 0 < tail[0] and board[head[0] - 1][head[1]] == 0 and board[tail[0] - 1][tail[1]] == 0:
        result.append(frozenset(((head[0] - 1, head[1]), (tail[0] - 1, tail[1]))))

    # 좌 이동
    if 0 < head[1] and 0 < tail[1] and board[head[0]][head[1] - 1] == 0 and board[tail[0]][tail[1] - 1] == 0:
        result.append(frozenset(((head[0], head[1] - 1), (tail[0], tail[1] - 1))))

    # 하 이동
    if head[0] < N - 1 and tail[0] < N - 1 and board[head[0] + 1][head[1]] == 0 and board[tail[0] + 1][tail[1]] == 0:
        result.append(frozenset(((head[0] + 1, head[1]), (tail[0] + 1, tail[1]))))

    # 우 이동
    if head[1] < N - 1 and tail[1] < N - 1 and board[head[0]][head[1] + 1] == 0 and board[tail[0]][tail[1] + 1] == 0:
        result.append(frozenset(((head[0], head[1] + 1), (tail[0], tail[1] + 1))))

    # 가로일 때 회전
    if head[0] == tail[0]:
        # 머리, 꼬리를 기준으로 상회전
        if head[0] > 0 and board[head[0] - 1][head[1]] == 0 and board[head[0] - 1][head[1] - 1] == 0:
            result.append(frozenset((head, (head[0] - 1, head[1]))))
            result.append(frozenset((tail, (tail[0] - 1, tail[1]))))

        # 머리, 꼬리를 기준으로 하회전
        if head[0] < N - 1 and board[head[0] + 1][head[1]] == 0 and board[head[0] + 1][head[1] - 1] == 0:
            result.append(frozenset((head, (head[0] + 1, head[1]))))
            result.append(frozenset((tail, (tail[0] + 1, tail[1]))))

    # 세로일때 회전
    if head[1] == tail[1]:
        # 머리, 꼬리를 기준으로 좌회전
        if head[1] > 0 and board[head[0]][head[1] - 1] == 0 and board[head[0] - 1][head[1] - 1] == 0:
            result.append(frozenset((head, (head[0], head[1] - 1))))
            result.append(frozenset((tail, (tail[0], tail[1] - 1))))

        # 머리, 꼬리를 기준으로 우회전
        if head[1] < N - 1 and board[head[0]][head[1] + 1] == 0 and board[head[0] - 1][head[1] + 1] == 0:
            result.append(frozenset((head, (head[0], head[1] + 1))))
            result.append(frozenset((tail, (tail[0], tail[1] + 1))))
    return result


def solution(board):
    N = len(board)
    queue = deque()
    visited = set()
    queue.append((frozenset(((0, 0), (0, 1))), 0))
    visited.add((frozenset(((0, 0), (0, 1))), 0))

    while queue:
        robot, move = queue.popleft()

        if (N - 1, N - 1) in robot:
            break

        for next_robot in peek(board, robot):
            if next_robot in visited:
                continue
            visited.add(next_robot)
            queue.append((next_robot, move + 1))

    return move

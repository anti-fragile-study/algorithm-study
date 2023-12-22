import math
from collections import defaultdict
from itertools import permutations


def up(board, r, c):
    return max(0, r - 1), c


def down(board, r, c):
    return min(r + 1, 3), c


def left(board, r, c):
    return r, max(c - 1, 0)


def right(board, r, c):
    return r, min(c + 1, 3)


def ctrl_up(board, r, c):
    for rr in range(r, 0, -1):
        if r == rr:
            continue
        if board[rr][c] != 0:
            return rr, c
    return 0, c


def ctrl_down(board, r, c):
    for rr in range(r, 4):
        if r == rr:
            continue
        if board[rr][c] != 0:
            return rr, c
    return 3, c

def ctrl_left(board, r, c):
    for cc in range(c, 0, -1):
        if cc == c:
            continue
        if board[r][cc] != 0:
            return r, cc
    return r, 0

def ctrl_right(board, r, c):
    for cc in range(c, 4):
        if c == cc:
            continue
        if board[r][cc] != 0:
            return r, cc
    return r, 3


def distance(r, c, dr, dc):
    return abs(r - dr) + abs(c - dc)


def find_cost(board, r, c, dr, dc):
    operations = [left, right, up, down, ctrl_left, ctrl_right, ctrl_up, ctrl_down]

    if r == dr and c == dc:
        return 0

    d = 100
    data = defaultdict(list)

    for operation in operations:
        tr, tc = operation(board, r, c)
        if tr == r and tc == c:
            continue
        td = distance(tr, tc, dr, dc)

        if d >= td:
            d = td
            data[td].append((tr, tc))
    return min(map(lambda x: find_cost(board, *x, dr, dc), data[d])) + 1


def find_all_steps(board, r, c, case, positions):
    if not case:
        return 0
    number = case[0]

    c1 = find_cost(board, r, c, *positions[number][0]) + find_cost(board, *positions[number][0], *positions[number][1])
    c2 = find_cost(board, r, c, *positions[number][1]) + find_cost(board, *positions[number][1], *positions[number][0])

    board[positions[number][0][0]][positions[number][0][1]] = 0
    board[positions[number][1][0]][positions[number][1][1]] = 0

    l1 = find_all_steps(board, *positions[number][1], case[1:], positions)
    l2 = find_all_steps(board, *positions[number][0], case[1:], positions)

    board[positions[number][0][0]][positions[number][0][1]] = case[0]
    board[positions[number][1][0]][positions[number][1][1]] = case[0]

    return min(c1 + l1, c2 + l2)


def solution(board, r, c):
    answer = math.inf
    cards = set()
    positions = [[] for _ in range(7)]

    for rr in range(4):
        for cc in range(4):
            if board[rr][cc] != 0:
                cards.add(board[rr][cc])
                positions[board[rr][cc]].append((rr, cc))

    for case in permutations(cards):
        steps = find_all_steps(board, r, c, case, positions)
        answer = min(answer, steps)

    return answer + 2 * len(cards)

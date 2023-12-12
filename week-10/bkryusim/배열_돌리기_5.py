import sys


def vertical_flip(board):
    return [board[len(board) - r - 1] for r in range(len(board))]


def horizontal_flip(board):
    return [[board[r][len(board[0]) - c - 1] for c in range(len(board[0]))] for r in range(len(board))]


def rotate_clock(board):
    return [[board[r][c] for r in range(len(board) - 1, -1, -1)] for c in range(len(board[0]))]


def rotate_anti(board):
    return [[board[r][c] for r in range(len(board))] for c in range(len(board[0]) - 1, -1, -1)]


def solve():
    N, M, R = map(int, sys.stdin.readline().split())
    original = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
    works = list(map(int, sys.stdin.readline().split()))
    operations = [None, vertical_flip, horizontal_flip, rotate_clock, rotate_anti, rotate_clock, rotate_anti]

    # 몇도 돌아갔는지, 좌우반전, 상하반전
    degree = 0
    horizontal = False
    vertical = False

    mini = [[1, 2], [3, 4]]
    partitions = [None,
                  [[original[r][c] for c in range(M // 2)] for r in range(N // 2)],
                  [[original[r][c] for c in range(M // 2, M)] for r in range(N // 2)],
                  [[original[r][c] for c in range(M // 2)] for r in range(N // 2, N)],
                  [[original[r][c] for c in range(M // 2, M)] for r in range(N // 2, N)],
                  ]

    for work in works:
        mini = operations[work](mini)

        if work == 1:
            if degree % 2 == 0:
                vertical = not vertical
            else:
                horizontal = not horizontal
        elif work == 2:
            if degree % 2 == 0:
                horizontal = not horizontal
            else:
                vertical = not vertical
        elif work == 3:
            degree = (degree + 1) % 4
        elif work == 4:
            degree = (degree - 1) % 4

    for n in 1, 2, 3, 4:
        if vertical:
            partitions[n] = vertical_flip(partitions[n])
        if horizontal:
            partitions[n] = horizontal_flip(partitions[n])
        for i in range(degree):
            partitions[n] = rotate_clock(partitions[n])

    if degree % 2 == 0:
        answer = [[0 for _ in range(M)] for _ in range(N)]

        for dr, dc, br, bc in (0, 0, 0, 0), (0, M // 2, 0, 1), (N // 2, 0, 1, 0), (N // 2, M // 2, 1, 1):
            for r in range(N // 2):
                for c in range(M // 2):
                    answer[r + dr][c + dc] = partitions[mini[br][bc]][r][c]
    else:
        answer = [[0 for _ in range(N)] for _ in range(M)]

        for dr, dc, br, bc in (0, 0, 0, 0), (0, N // 2, 0, 1), (M // 2, 0, 1, 0), (M // 2, N // 2, 1, 1):
            for r in range(M // 2):
                for c in range(N // 2):
                    answer[r + dr][c + dc] = partitions[mini[br][bc]][r][c]

    for row in answer:
        print(' '.join(map(str, row)))


solve()

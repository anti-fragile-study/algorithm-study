def rotate(board):
    return list(zip(*board[::-1]))


def solution(key, lock):
    lock_size = len(lock)
    key_size = len(key)
    size = lock_size + 2 * (key_size - 1)

    newlock = [[0 for _ in range(size)] for _ in range(size)]

    for r in range(lock_size):
        for c in range(lock_size):
            newlock[r + key_size - 1][c + key_size - 1] = lock[r][c]

    for _ in range(4):
        key = rotate(key)

        for r in range(lock_size + key_size - 1):
            for c in range(lock_size + key_size - 1):
                for rr in range(key_size):
                    for cc in range(key_size):
                        newlock[r + rr][c + cc] += key[rr][cc]

                opened = True
                for rr in range(key_size - 1, key_size + lock_size - 1):
                    for cc in range(key_size - 1, key_size + lock_size - 1):
                        if newlock[rr][cc] != 1:
                            opened = False
                            break
                if opened:
                    return True

                for rr in range(key_size):
                    for cc in range(key_size):
                        newlock[r + rr][c + cc] -= key[rr][cc]
    return False

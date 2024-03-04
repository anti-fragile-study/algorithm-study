from itertools import combinations
from collections import deque

dirs = [(0, -1), (0, +1), (-1, 0), (+1, 0)]

def inBound(coord, bound):
    return coord[0] >= 0 and coord[1] >= 0 and coord[0] < bound and coord[1] < bound

def spread_virus(virus_set, board, empty_count):
    queue = deque()
    visited = [[False for _ in range(len(board))] for _ in range(len(board))]
    for virus in virus_set:
        queue.append((virus[0], virus[1], 0))
        visited[virus[0]][virus[1]] = True
        empty_count -= 1
    
    time = 0
    while queue:
        curr = queue.popleft()
        time = curr[2]

        for dir in dirs:
            next = (curr[0] + dir[0], curr[1] + dir[1], curr[2] + 1)
            if not inBound(next, len(board)):
                continue
            if visited[next[0]][next[1]] or board[next[0]][next[1]] == 1:
                continue
            queue.append(next)
            empty_count -= 1
            visited[next[0]][next[1]] = True

    return time if empty_count == 0 else float('inf')

N, M = map(int, input().split(' '))

board = []
virus_starts = []
empty_count = 0

for row in range(N):
    board.append(list(map(int, input().split(' '))))
    for col in range(N):
        if board[row][col] == 2:
            virus_starts.append((row, col))
            board[row][col] = 0
        if board[row][col] == 0:
            empty_count += 1

answer = float('inf')

for virus_set in combinations(virus_starts, M):
    answer = min(answer, spread_virus(virus_set, board.copy(), empty_count))

print(answer if answer != float('inf') else -1)

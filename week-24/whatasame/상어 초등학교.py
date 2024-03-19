# 학생의 만족도 총 합을 반환

# 인접 좋아하는 학생
# 인접 빈 칸 많은
# 행 번호 -> 열 번호 작은 순

import sys
from collections import defaultdict

input = sys.stdin.readline
directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]

n = int(input())
data = {}
for _ in range(n ** 2):
    nums = list(map(int, input().split()))
    data[nums[0]] = set(nums[1:])

def solution(n, data):
    board = [[None for _ in range(n)] for _ in range(n)]

    for student, likes in data.items():
        r, c = where(board, likes)
        board[r][c] = student

    return cal_score(board, data)

def where(board, likes):
    # 인접 좋아하는 학생
    like_score = defaultdict(list)
    for r in range(n):
        for c in range(n):
            if board[r][c]:
                continue
            cnt = sum(board[nr][nc] in likes for nr, nc in adjacent(r, c))
            like_score[cnt].append((r, c))
    max_like = max(like_score)
    if len(like_score[max_like]) == 1: # 후보 칸이 하나라면 바로 반환
        return like_score[max_like][0]

    # 인접 빈 칸 많은 순
    blank_score = defaultdict(list)
    for r, c in like_score[max_like]:
        if board[r][c]:
            continue

        cnt = sum(not board[nr][nc] for nr, nc in adjacent(r, c))
        blank_score[cnt].append((r, c))

    return min(blank_score[max(blank_score)]) # 행, 열 번호 작은 순

def adjacent(r, c):
    return [(r + dr, c + dc) for dr, dc in directions if 0 <= r + dr < n and 0 <= c + dc < n]

def cal_score(board, data):
    score = 0
    for r, row in enumerate(board):
        for c, student in enumerate(row):
            cnt = sum(board[nr][nc] in data[student] for nr, nc in adjacent(r, c))
            if cnt:
                score += 10 ** (cnt - 1)

    return score

print(solution(n, data))

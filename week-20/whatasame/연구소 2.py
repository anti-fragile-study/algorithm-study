# 연구소 모든 칸에 바이러스가 퍼지는 최소 시간을 출력

# 상하좌우로 이동, 1초 소요
# 0: 빈 칸, 1: 벽, 2: 바이러스 가능

# 10 >= 바이러스를 놓을 수 있는 칸 >= m

"""
바이러스를 놓자마자 바로 완료된 경우
5 2
1 1 1 1 1
1 1 2 1 1
1 1 2 1 1
1 1 1 1 1
1 1 1 1 1
-> 0
"""

import sys
from itertools import combinations
from heapq import *
from copy import deepcopy

input = sys.stdin.readline
n, m = map(int, input().split())
labs = [list(map(int, input().split())) for _ in range(n)]
directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]

def solution(labs):
    answer = 987654321

    # 바이러스를 놓을 수 있는 좌표, 빈 칸 개수 구하기: n^2
    candidates = []
    blank_cnt = 0
    for r, row in enumerate(labs):
        for c, val in enumerate(row):
            if val == 2:
                candidates.append((r, c))
            elif val == 0:
                blank_cnt += 1

    # 가능한 바이러스의 좌표에 대하여: 10Cm <= 10C5 <= 30,000
    for cases in combinations(candidates, m):
        # 선택한 바이러스와 선택하지 않은 바이러스 초기화
        board = deepcopy(labs)
        for r, c in cases:
            board[r][c] = 2
        for r, c in set(candidates) - set(cases):
            board[r][c] = 0

        # 남은 빈 칸 수 = 빈 칸 개수 + 선택받지 못한 좌표 수: 1
        remain = blank_cnt + len(candidates) - m
        if remain == 0:
            return 0

        # 각 좌표에 대하여 BFS (시간대, 좌표): n^2
        timer = 0
        hq = []
        for case in cases:
            heappush(hq, (timer, case))

        while hq:
            timer, (r, c) = heappop(hq)

            for dr, dc in directions:
                nr, nc = r + dr, c + dc

                if not (0 <= nr < n and 0 <= nc < n) or board[nr][nc] != 0:
                    continue

                board[nr][nc] = 2
                heappush(hq, (timer + 1, (nr, nc)))
                remain -= 1

                # 만약 빈 칸이 없다면 시간대 저장: 1
                if remain == 0:
                    answer = min(answer, timer + 1)

    # 가장 짧은 시간 반환
    return answer if answer != 987654321 else -1

print(solution(labs))

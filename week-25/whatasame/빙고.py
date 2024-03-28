# 세 개의 줄이 그어지는 순간 해당 숫자의 순서를 반환

import sys

input = lambda:sys.stdin.readline().strip()

board = [list(map(int, input().split())) for _ in range(5)]
nums = [int(num) for _ in range(5) for num in input().split()]

def solution(board, nums):
    # 인덱스 맵 선언
    info = {board[r][c]: (r, c) for r in range(5) for c in range(5)}

    # 긋는 보드 선언
    checked = [[0 for _ in range(5)] for _ in range(5)]

    # 모든 숫자에 대하여
    for idx, num in enumerate(nums):
        # 숫자 긋기
        r, c = info[num]
        checked[r][c] = 1

        cnt = 0
        # 가로 확인
        cnt += sum(1 for r in range(5) if sum(checked[r]) == 5)
        # 세로 확인
        cnt += sum(1 for c in range(5) if sum(checked[r][c] for r in range(5)) == 5)

        # 대각선 확인
        if checked[0][0] + checked[1][1] + checked[2][2] + checked[3][3] + checked[4][4] == 5:
            cnt += 1
        if checked[0][4] + checked[1][3] + checked[2][2] + checked[3][1] + checked[4][0] == 5:
            cnt += 1

        # 3개 이상이면 해당 순서 반환
        if cnt >= 3:
            return idx + 1

print(solution(board, nums))

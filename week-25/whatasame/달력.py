# 모든 일정을 포함하는 가장 작은 직사각형의 면적을 구하라

# 최종적으로 구해야하는 것은 연속된 일정에서 최대 일정 수 (세로)와 연속된 일정의 일 수 (길이)
# 따라서, 연속된 일정은 알아서 빈 칸에 들어간다고 가정해도 무방하다.

"""
5
1 9
8 9
4 6
7 7
2 5
-> 27

7
4 5
2 4
5 6
5 7
7 9
11 12
12 12
-> 28
"""

import sys

input = lambda:sys.stdin.readline().strip()

n = int(input())
schedules = [tuple(map(int, input().split())) for _ in range(n)]

def solution():
    # 날짜별 일정 개수 구하기
    cnt = [0 for _ in range(366)] # 0 ~ 365
    for start, end in schedules:
        for day in range(start, end + 1):
            cnt[day] += 1

    # 코팅지 면적 구하기
    width, height = 0, 0
    answer = 0
    for day in range(1, 366): # 모든 날에 대하여 -> O(1)
        # 일정이 없다면 코팅지 면적 더하기, 높이 0
        if cnt[day] == 0:
            answer += width * height
            width, height = 0, 0
            continue

        width += 1
        height = max(height, cnt[day])
        if day == 365: # 마지막 날에는 무조건 계산
            answer += width * height

    return answer

print(solution())

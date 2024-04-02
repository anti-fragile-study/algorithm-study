# n is odd
# (1, 1) ~ (n, n)
# 마법사 상어: 정중앙
# 블리자드를 쓰면 d방향으로 s개가 없어진다
# 연속하는 구슬이 4개 이상 있을 때 폭발한다
# 연속하는 구슬의 개수와 번호로 변한다.

# 블리자드를 M번 시전했을 때 폭발한 구슬의 개수 * 각 구슬의 번호의 합을 반환

# 구슬이 이동하는 건 리스트로 이동시키고 리스트를 다시 배열로 바꾸는 방식은 어떨까

import sys

input = lambda: sys.stdin.readline().strip()

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
blizzards = [tuple(map(int, input().split())) for _ in range(m)]


def solution(arr, blizzards):
    answer = {1: 0, 2: 0, 3: 0}

    # 상어 위치 초기화
    center = n // 2
    arr[center][center] = -1

    # 각 블리자드에 대하여
    for d, s in blizzards:
        # 블리자드 사용
        if d == 1:  # 상
            for r in range(center - 1, center - 1 - s, -1):
                arr[r][center] = 0
        if d == 2:  # 하
            for r in range(center + 1, center + 1 + s):
                arr[r][center] = 0
        if d == 3:  # 좌
            for c in range(center - 1, center - 1 - s, -1):
                arr[center][c] = 0
        if d == 4:  # 우
            for c in range(center + 1, center + 1 + s):
                arr[center][c] = 0

        # 리스트로 변환
        line = to_line(arr) # 밖에서 안으로

        # 연속하는 구슬 폭발
        line = explode(answer, line)

        # 구슬 변화
        reversed_line = []  # 안에서 밖으로
        num, count = line[-1], 0
        for idx, val in enumerate(line[::-1]):
            if val != num:
                reversed_line.append(count)
                reversed_line.append(num)
                num, count = val, 1
            else:
                count += 1
        reversed_line.append(count)
        reversed_line.append(num)

        reversed_line = [-1] + reversed_line # 상어 붙여주기
        reversed_line = reversed_line[:n ** 2]  # 길이 제한
        reversed_line = reversed_line + [0] * (n ** 2 - len(reversed_line))  # 부족한 개수는 0으로 채워넣기

        # 배열로 변환
        arr = to_arr(reversed_line[::-1])

    return answer[1] + 2 * answer[2] + 3 * answer[3]


def to_line(arr):
    r, c = 0, 0

    direction = 1  # 우하좌상 = 1234
    line = []
    while arr[r][c] != -1: # 상어 제외
        # 값 추가
        line.append(arr[r][c])
        arr[r][c] = None

        # out of idx이거나 방문한 곳이라면 방향 전환
        if direction == 1 and (c + 1 >= n or arr[r][c + 1] is None):
            direction = 2
        if direction == 2 and (r + 1 >= n or arr[r + 1][c] is None):
            direction = 3
        if direction == 3 and (c - 1 < 0 or arr[r][c - 1] is None):
            direction = 4
        if direction == 4 and (r - 1 < 0 or arr[r - 1][c] is None):
            direction = 1

        # 이동
        if direction == 1:
            c += 1
        if direction == 2:
            r += 1
        if direction == 3:
            c -= 1
        if direction == 4:
            r -= 1

    return [val for val in line if val != 0] # 블리자드에서 없어진 빈 칸 제거

def explode(answer, line):
    # 연속되는 그룹 찾기
    exploded = False
    num, indices = line[0], []
    for idx, val in enumerate(line):
        # 다른 숫자거나 마지막 원소일 때 그룹 구분
        if val != num:
            # 조건에 맞으면 폭발
            if len(indices) >= 4:
                for i in indices:
                    line[i] = 0
                answer[num] += len(indices)
                exploded = True
            num, indices = val, [idx]
        else:
            indices.append(idx)
    if len(indices) >= 4:
        for i in indices:
            line[i] = 0
        answer[num] += len(indices)
        exploded = True

    # 0으로 표시된 원소 제거
    line = [val for val in line if val != 0]

    # 폭발한 대상이 없다면 재귀 종료
    if not exploded:
        return line

    # 재귀적으로 수행
    return explode(answer, line)

def to_arr(line):
    arr = [[None for _ in range(n)] for _ in range(n)]

    r, c = 0, 0
    direction = 1  # 우하좌상 = 1234
    for val in line:
        arr[r][c] = val

        # out of idx이거나 방문한 곳이라면 방향 전환
        if direction == 1 and (c + 1 >= n or arr[r][c + 1] is not None):
            direction = 2
        if direction == 2 and (r + 1 >= n or arr[r + 1][c] is not None):
            direction = 3
        if direction == 3 and (c - 1 < 0 or arr[r][c - 1] is not None):
            direction = 4
        if direction == 4 and (r - 1 < 0 or arr[r - 1][c] is not None):
            direction = 1

        # 이동
        if direction == 1:
            c += 1
        if direction == 2:
            r += 1
        if direction == 3:
            c -= 1
        if direction == 4:
            r -= 1

    return arr

print(solution(arr, blizzards))

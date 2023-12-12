import sys
input = sys.stdin.readline

N, H = map(int, input().split())
arr = [int(input()) for _ in range(N)]

up = [0] * (H + 1)
down = [0] * (H + 1)

for i in range(1, N+1):
    n = arr[i-1]
    if i % 2 == 0:
        up[n] += 1
    else:
        down[n] += 1

for i in range(H-1, 0, -1):
    up[i] += up[i+1]
    down[i] += down[i+1]

# 장애물 최소 값, 구간 개수
obstacle, count = N, 0
for i in range(1, H+1):
    temp = up[H - i + 1] + down[i]
    if obstacle > temp:
        obstacle = temp
        count = 1
    elif obstacle == temp:
        count += 1

print(*[obstacle, count])
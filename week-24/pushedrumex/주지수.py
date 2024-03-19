import sys
input = sys.stdin.readline

N, M = map(int, input().split())

ground = [[0] * (M+1)]
for _ in range(N):
    ground.append([0] + list(map(int, input().split())))

for i in range(1, N+1):
    for j in range(1, M+1):
        ground[i][j] += ground[i-1][j] + ground[i][j-1] - ground[i-1][j-1]

K = int(input())
for _ in range(K):
    x1, y1, x2, y2 = map(int, input().split())
    print(ground[x2][y2] - ground[x1-1][y2] - ground[x2][y1-1] + ground[x1-1][y1-1])

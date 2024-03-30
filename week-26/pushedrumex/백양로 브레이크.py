# 플로이드워샬
# 일방통행, 기존양방통행 비용 = 0
# 새로운양방통행 비용 = 1

n, m = map(int, input().split())

distance = [[int(1e9)] * (n+1) for _ in range(n+1)]
for i in range(1, n+1):
    distance[i][i] = 0

for _ in range(m):
    u, v, b = map(int, input().split())
    distance[u][v] = 0
    if b == 1:
        distance[v][u] = 0
    else:
        distance[v][u] = 1

for k in range(1, n+1):
    for p1 in range(1, n+1):
        for p2 in range(1, n+1):
            distance[p1][p2] = min(distance[p1][p2], distance[p1][k] + distance[k][p2])

k = int(input())
for _ in range(k):
    s, e = map(int, input().split())
    print(distance[s][e])
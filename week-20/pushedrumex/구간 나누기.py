INF = int(1e9)
N, M = map(int, input().split())

# 자기 자신을 포함하지 않음
dp1 = [[0] + [-INF] * M for _ in range(N+1)]
# 자기 자신 포함
dp2 = [[0] + [-INF] * M for _ in range(N+1)]

# 수열 인덱스
for i in range(1, N+1):
    num = int(input())
    # 구간 개수
    for j in range(1, min(M, (i+1) // 2) + 1):
        # 자기 자신을 포함하지 않은 것중 최대
        dp1[i][j] = max(dp1[i-1][j], dp2[i-1][j])
        # 자기 자신을 포함하되, 전의 값과 구간을 잇는거와 나누는 것 중 최대
        dp2[i][j] = max(dp2[i-1][j], dp1[i-1][j-1]) + num

print(max(dp1[N][M], dp2[N][M]))
# M = 10억

N, M = map(int, input().split())
q = []
times = [int(input()) for _ in range(N)]

left, right = 1, min(times) * M
while left <= right:
    mid = (left+right) // 2
    # mid 시간 동안 받을 수 있는 심사의 최대 수
    count = 0
    for time in times:
        count += mid // time
        
    if count < M:
        left = mid + 1
    else:
        result = mid
        right = mid - 1

print(result)
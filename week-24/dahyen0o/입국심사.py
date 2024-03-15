N, M = map(int, input().split())
times = [int(input()) for _ in range(N)]

MAX_M = 10 ** 18
left = 0
right = MAX_M
answer = 0

while left <= right:
    mid = (left + right) // 2
    
    # mid 시간 내에 모든 검사를 완료할 수 있는지 검사
    count = sum(map(lambda time: mid // time, times))
    if count >= M:
        answer = mid
        right = mid - 1
    else:
        left = mid + 1

print(answer)

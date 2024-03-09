# N = 100,000
# N * N = 10,000,000,000
# logN = 33

import sys
input = sys.stdin.readline

N = int(input())
k = int(input())

left, right = 1, N*N
result = right
while left <= right:
    mid = (left + right) // 2

    # 한 행씩 탐색하면서 mid 이하인 수의 개수 ++
    count = 0
    for i in range(1, N+1):
        count += min(N, mid // i)
    if count >= k:
        right = mid - 1
        result = mid
    else:
        left = mid + 1

print(result)
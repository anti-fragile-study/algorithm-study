n, k = int(input()), int(input())

"""
1
1
-> 1

3
9
-> 9

3
2
-> 2

3
6
-> 4
"""

def solution(n, k):
    left, right = 1, k

    while left <= right:
        mid = (left + right) // 2

        cnt = sum([min(mid // i, n) for i in range(1, n + 1)])
        if cnt >= k:
            right = mid - 1
        else:
            left = mid + 1

    return left

print(solution(n, k))

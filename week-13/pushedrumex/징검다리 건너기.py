def solution(stones, k):
    answer = 0
    left, right = 0, 200_000_000
    
    while left <= right:
        mid = (left + right) // 2
        if is_possible(stones, mid, k):
            answer = mid
            left = mid + 1
        else:
            right = mid - 1
    
    return answer

def is_possible(stones, n, k):
    zero = 0
    for stone in stones:
        if stone >= n:
            zero = 0
        else:
            zero += 1
            if zero >= k:
                return False
    return True
            
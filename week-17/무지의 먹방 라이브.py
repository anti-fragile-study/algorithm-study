# 파이썬 1초에 2000만
# 이분탐색 O(logN)
# 200,000 * log(100,000,000) = 5,200,000
def solution(food_times, k):
    left, right = 1, int(1e8)
    N = len(food_times)
    rotate, t = 0, 0
    times = sorted(food_times)
    while left <= right:
        mid = (left + right) // 2
        temp = N * mid
        for time in times:
            if time >= mid:
                break
            temp -= mid - time
        
        if temp <= k:
            rotate = mid
            t = temp
            left = mid + 1
        else:
            right = mid - 1
    
    k -= t
    for i in range(len(food_times)):
        food_times[i] -= rotate
        if food_times[i] > 0:
            k -= 1
        if k < 0:
            return i + 1

    return -1
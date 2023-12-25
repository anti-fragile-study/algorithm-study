def solution(distance, rocks, n):
    answer = 0
    rocks.sort()
    rocks = [0] + rocks + [distance]
    
    left, right = 0, distance
    while left <= right:
        mid = (left + right) // 2
        if is_possible(rocks, n, mid):
            answer = mid
            left = mid + 1
        else:
            right = mid - 1
        
    return answer

# d 가 최소값으로 유망한 거리인가?
def is_possible(rocks, n, d):
    idx = 0
    while idx < len(rocks) - 1:
        _idx = idx + 1
        # d 이상이 될 때까지
        while _idx < len(rocks) and rocks[_idx] - rocks[idx] < d:
            # 아직 d 이상이 안됐는데, 더이상 바위를 깰 수 없음
            if n == 0:
                return False
            n -= 1
            _idx += 1
        idx = _idx
    return True
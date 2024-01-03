# 디딤돌의 숫자만큼 밟을 수 있다
# 밟지 못하는 디딤돌은 건너뛴다.
# 밟을 수 있는 디딤돌이 여러 개인 경우 가장 가까운 디딤돌만 가능하다.

# 디딤돌 당 밟을 수 있는 최대 횟수: 2억

# 밟지 못하는 디딤돌이 k개 있으면 못 건넌다.

def solution(stones, k):
    left, right = 1, 200_000_000

    answer = None
    while left <= right:
        middle = (left + right + 1) // 2

        # middle - 1번째 친구가 건넜다고 가정했을 때 움직일 수 있다면
        if movable(run(stones, middle - 1), k):
            answer = middle
            left = middle + 1
        else:
            right = middle - 1

    return answer

def movable(stones, k):
    zero_cnt = 0
    for stone in stones:
        if stone == 0:
            zero_cnt += 1
        else:
            zero_cnt = 0
        if zero_cnt >= k:
            return False

    return True

def run(stones, m):
    return [stone - m if stone >= m else 0 for stone in stones]

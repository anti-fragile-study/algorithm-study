# pop + insert = 1회

# 큰 쪽에서 계속 작은 쪽을 주다보면 같아진다?

from collections import deque


def solution(queue1, queue2):
    answer = 0
    sum1, sum2 = sum(queue1), sum(queue2)
    q1, q2 = deque(queue1), deque(queue2)

    # 같아질 때까지
    while sum1 != sum2:
        # 큰 쪽이 작은 쪽에게 주기
        if sum1 > sum2:
            num = q1.popleft()
            sum1 -= num
            sum2 += num
            q2.append(num)
        elif sum1 < sum2:
            num = q2.popleft()
            sum1 += num
            sum2 -= num
            q1.append(num)
        answer += 1

        # 구할 수 없을 때는 무한 루프
        if answer > 600_000:
            return -1

    return answer

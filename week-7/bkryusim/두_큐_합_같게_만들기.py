from collections import deque


def solution(queue1, queue2):
    answer = 0

    max_try = len(queue1) + len(queue2) + 1

    sum1 = sum(queue1)
    sum2 = sum(queue2)

    if (sum1 + sum2) % 2 == 1:
        return -1

    q1 = deque(queue1)
    q2 = deque(queue2)

    while sum1 != sum2:
        if answer > max_try:
            return -1

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

    return answer

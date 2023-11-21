from collections import deque

def solution(queue1, queue2):
    sum1 = sum(queue1)
    sum2 = sum(queue2)
    
    if (sum1 + sum2) % 2 != 0: return -1
    N = len(queue1)
    q1 = deque(queue1)
    q2 = deque(queue2)
    
    target = (sum1 + sum2) / 2
    answer = 0
    while sum1 != target:
        if sum1 > target:
            temp = q1.popleft()
            q2.append(temp)
            sum1 -= temp
        else:
            temp = q2.popleft()
            q1.append(temp)
            sum1 += temp
        answer += 1
        if answer > 4 * N: return -1
    return answer
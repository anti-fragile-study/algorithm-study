from collections import deque

def solution(s):
    q1 = deque()
    q2 = deque(list(s))
    
    while q2:
        if not q1:
            q1.append(q2.popleft())
        if q2:
            if q1[-1] == q2[0]:
                q1.pop()
                q2.popleft()
            else:
                q1.append(q2.popleft())

    if len(q1) == 0:
        return 1
    
    return 0
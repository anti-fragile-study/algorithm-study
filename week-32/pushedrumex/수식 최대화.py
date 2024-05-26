from itertools import permutations
from collections import deque

OP = ["+", "-", "*"]
def solution(expression):
    answer = 0
    q = deque(expression)
    init_q = deque()
    while q:
        temp = q.popleft()
        if temp in OP:
            init_q.append(temp)
            continue
        while q and q[0] not in OP:
            temp += q.popleft()
        init_q.append(int(temp))
    
    for cals in permutations(OP, 3):
        before_q = deque(init_q)
        for cal in cals:
            after_q = deque()
            while before_q:
                temp = before_q.popleft()
                if temp == cal:
                    n1 = after_q.pop()
                    n2 = before_q.popleft()
                    if cal == "+":
                        result = n1 + n2
                    elif cal == "-":
                        result = n1 - n2
                    elif cal == "*":
                        result = n1 * n2
                    after_q.append(result)
                else:
                    after_q.append(temp)
            before_q = after_q
        
        answer = max(answer, abs(after_q[0]))
    return answer
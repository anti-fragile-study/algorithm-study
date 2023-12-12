from itertools import permutations
from collections import deque

def solution(expression):
    operators = ("-", "+", "*")
    q = deque()
    temp = ""
    for s in expression:
        if s in operators:
            q.append(int(temp))
            q.append(s)
            temp = ""
        else:
            temp += s
    q.append(int(temp))
    answer = 0
    for orders in permutations(operators, 3):
        new_q = q.copy()

        for _operator in orders:
            temp_q = new_q.copy()
            new_q.clear()
            while temp_q:
                now = temp_q.popleft()
                if now == _operator:
                    a, b = new_q.pop(), temp_q.popleft()
                    if now == "-":
                        _answer = a - b
                    elif now == "+":
                        _answer = a + b
                    else:
                        _answer = a * b
                    new_q.append(_answer)
                else:
                    new_q.append(now)
        answer = max(answer, abs(_answer))
    return answer
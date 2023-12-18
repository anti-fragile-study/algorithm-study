from collections import deque

dic1 = {"(" : 1, ")" : -1}
dic2 = {"(" : ")", ")" : "("}

def solution(p):    
    answer = recur(deque(list(p)))
    return "".join(answer)

def recur(q):
    if len(q) == 0:
        return q
    u = deque([q.popleft()])
    temp = dic1[u[0]]
    while temp != 0 and q:
        u.append(q.popleft())
        temp += dic1[u[-1]]
    if isRight(u):
        return u + recur(q)
    u.popleft()
    u.pop()
    result = deque(["("]) + recur(q) + deque([")"])
    while u:
        temp = u.popleft()
        result.append(dic2[temp])
        
    return result
        
def isRight(q):
    temp = 0
    for x  in q:
        temp += dic1[x]
        if temp < 0:
            return False        
    return True

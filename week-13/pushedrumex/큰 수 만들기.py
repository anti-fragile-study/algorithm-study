def solution(number, k):
    q = []
    for n in number:
        while q and k > 0 and n > q[-1]:
            k -= 1
            q.pop()
        q.append(n)
        
    return "".join(q[:len(number) - k])
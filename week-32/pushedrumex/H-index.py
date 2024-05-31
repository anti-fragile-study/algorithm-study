def solution(citations):
    N = len(citations)
    count = [0] * (10001)
    for citation in citations:
        count[citation] += 1
    for i in range(1, 10001):
        count[i] += count[i-1]
    
    answer = 0
    for h in range(10001):
        more = count[-1]
        if h > 0:
            more -= count[h-1]
        if h <= more:
            answer = h

    return answer
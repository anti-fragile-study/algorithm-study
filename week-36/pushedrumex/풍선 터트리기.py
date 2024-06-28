from heapq import heappush, heappop
def solution(a):
    N = len(a)
    answer = 0
    left_min = 10**9
    visited = {}
    hq = []
    for i in range(N):
        visited[a[i]] = True
        heappush(hq, a[i])
        
    for i in range(N):
        n = a[i]
        left_min = min(left_min, n)
        while not visited[hq[0]]:
            heappop(hq)
        right_min = hq[0]
        if not (left_min < n and right_min < n):
            answer += 1
        visited[n] = False
    return answer
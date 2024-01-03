import heapq
from collections import deque

def solution(jobs):
    N = len(jobs)
    jobs = deque(sorted(jobs))
    now = 0
    answer = 0
    q = []
    while jobs or q:
        # 지금 바로 시작할 수 있는 작업 push
        while jobs and jobs[0][0] <= now:
            job = jobs.popleft()
            heapq.heappush(q, (job[1], job[0]))
        if q:
            # q 의 job 실행
            t, request = heapq.heappop(q)
            answer += (now - request) + t
            now += t
        else:
            now += 1
    return answer // N
import heapq
from collections import deque


def solution(jobs):
    answer = 0
    current = 0
    l = len(jobs)

    jobs.sort(key=lambda x: x[0])
    jobs = deque(jobs)
    booked = []

    while jobs or booked:
        while jobs and jobs[0][0] <= current:
            heapq.heappush(booked, (jobs[0][1], jobs[0]))
            jobs.popleft()

        if not booked:
            current = jobs[0][0]
            continue
        else:
            task = heapq.heappop(booked)[1]
            current += task[1]
            answer += current - task[0]

    return answer // l
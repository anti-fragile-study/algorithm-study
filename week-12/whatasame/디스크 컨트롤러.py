# 응답시간의 평균이 가장 작을 때 평균을 반환
# 소수점은 버린다

# job = [요청시간, 소요시간]
# 비어있을 땐 먼저 들어온 요청부터 수행

"""
[[2, 7]]
-> 7

[[0, 1], [0, 3], [0, 5]]
-> (1 + 4 + 9 = 14) / 3 = 4

[[0, 1], [2, 3], [2, 5]]
-> (1 + 3 + 10 = 14) / 3 = 4

[[1, 3], [11, 33], [50, 5]]
-> (3 + 33 + 5 = 41) / 3 = 13

[[1, 9], [2, 5], [3, 4]]
-> (9 + 17 + 11 = 37) / 3 = 12
"""

from heapq import *

def solution(jobs):
    n = len(jobs)

    # jobs를 시작 시간 순으로 정렬
    heapify(jobs)

    heap = []
    now, res = 0, 0
    # jobs 혹은 heap을 모두 소모할 때까지
    while jobs or heap:
        # 현재 처리할 수 있는 요청 모두 힙에 넣기
        for req, time in find_targets(jobs, now):
            heappush(heap, (time ,req))

        # 힙이 비어있다면 1초 째깍
        if not heap:
            now += 1

        # 힙 소모
        if heap:
            time, req = heappop(heap)

            # 현재 시간 갱신
            now += time

            # 응답 시간 갱신
            res += now - req

    return res // n

def find_targets(jobs, time):
    if not jobs:
        return []

    targets = []
    while jobs:
        if jobs[0][0] > time:
            break
        targets.append(heappop(jobs))

    return targets

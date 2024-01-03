import math
from heapq import heappush, heappop


def solution(stones, k):
    answer = math.inf
    heap = []

    for i, stone in enumerate(stones):
        heappush(heap, (-stone, i))
        if i < k - 1:
            continue
        while heap[0][1] < i - k + 1:
            heappop(heap)
        answer = min(answer, -heap[0][0])

    return answer

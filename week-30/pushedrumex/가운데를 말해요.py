from heapq import heappop, heappush
import sys

input = sys.stdin.readline
N = int(input())
maxhq = []
minhq = []
for i in range(N):
    n = int(input())
    if len(maxhq) == len(minhq):
        heappush(maxhq, -n)
    else:
        heappush(minhq, n)
    
    if maxhq and minhq and -maxhq[0] > minhq[0]:
        max_value = -heappop(maxhq)
        min_value = heappop(minhq)
        heappush(minhq, max_value)
        heappush(maxhq, -min_value)

    print(-maxhq[0])

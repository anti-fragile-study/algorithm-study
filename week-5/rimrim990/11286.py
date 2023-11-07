# 백준 11286 절댓값 힙
import sys
from heapq import heappop, heappush
input = sys.stdin.readline

n = int(input())
hq = []

for _ in range(n):
    cmd = int(input())

    # 절댓값이 가장 작은 값을 출력한다.
    if cmd == 0:
        abs_x, x = heappop(hq) if hq else (0, 0)
        print(x)

    # 배열에 값을 추가한다.
    else:
        heappush(hq, (abs(cmd), cmd))
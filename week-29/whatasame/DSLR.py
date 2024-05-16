# heapq vs deque: https://www.acmicpc.net/board/view/116324

import sys
from collections import deque

input = lambda:sys.stdin.readline().strip()

t = int(input())
cases = [tuple(map(int, input().split())) for _ in range(t)]

def solution(cases):
    return [run(*case) for case in cases]

def run(origin, target):
    visited = [False] * 10_000
    q = deque([(origin, "")])
    while q:
        num, commands = q.popleft()

        if num == target:
            return commands

        d_num = num * 2 % 10_000
        if not visited[d_num]:
            visited[d_num] = True
            q.append((d_num, commands + "D"))

        s_num = 9_999 if num == 0 else num - 1
        if not visited[s_num]:
            visited[s_num] = True
            q.append((s_num, commands + "S"))

        l_num = num % 1000 * 10 + num // 1000
        if not visited[l_num]:
            visited[l_num] = True
            q.append((l_num, commands + "L"))

        r_num = num % 10 * 1000 + num // 10
        if not visited[r_num]:
            visited[r_num] = True
            q.append((r_num, commands + "R"))

print("\n".join(solution(cases)))

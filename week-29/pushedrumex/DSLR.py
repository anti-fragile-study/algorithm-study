from collections import deque, defaultdict
import sys
intput = sys.stdin.readline

T = int(input())
for _ in range(T):
    A, B = map(int, input().split())

    visited = defaultdict(bool)
    q = deque([(A, "")])
    while q:
        now, cmd = q.popleft()
        if now == B:
            print(cmd)
            break
        
        D = now * 2 % 10000
        S = 9999 if now == 0 else now - 1

        s = ("0000" + str(now))[-4:]
        L = int(s[1:] + s[0])
        R = int(s[-1] + s[:-1])

        if not visited[D]:
            visited[D] = True
            q.append((D, cmd + "D"))

        if not visited[S]:
            visited[S] = True
            q.append((S, cmd + "S"))

        if not visited[L]:
            visited[L] = True
            q.append((L, cmd + "L"))

        if not visited[R]:
            visited[R] = True
            q.append((R, cmd + "R"))
from collections import defaultdict

N, M = map(int, input().split())
folders = defaultdict(list)

for _ in range(N+M):
    P, F, C = input().split()
    folders[P].append((F, C))

Q = int(input())
def dfs(folder):
    global f, t
    for F, C in folders[folder]:
        if C == "1":
            dfs(F)
        else:
            f += 1
            if not visited[F]:
                visited[F] = True
                t += 1

for _ in range(Q):
    f, t = 0, 0
    folder = input().split("/")[-1]
    visited = defaultdict(bool)
    dfs(folder)
    print(*[t, f])
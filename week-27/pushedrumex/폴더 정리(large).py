from collections import defaultdict

N, M = map(int, input().split())
folders = defaultdict(list)
for _ in range(N+M):
    P, F, C = input().split()
    folders[P].append((F, C))

K = int(input())
for _ in range(K):
    A, B = input().split()
    A = A.split("/")[-1]
    B = B.split("/")[-1]

    visited = defaultdict(bool)
    for F, C in folders[B]:
        visited[(F, C)] = True
    
    for F, C in folders[A]:
        if C == "1":
            folders[B].append((F, C))
        elif not visited[(F, C)]:
            folders[B].append((F, C))

    folders[A] = []

Q = int(input())

def dfs(folder):
    global t, f

    for F, C in folders[folder]:
        if C == "1":
            dfs(F)
        else:
            f += 1
            if not visited[F]:
                visited[F] = True
                t += 1

for _ in range(Q):
    folder = input().split("/")[-1]
    t, f = 0, 0
    visited = defaultdict(bool)
    dfs(folder)
    print(*[t, f])
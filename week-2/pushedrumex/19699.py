from itertools import combinations

N, M = map(int, input().split())
H = list(map(int, input().split()))

root = int(9000 ** 0.5)
prime = [True] * (9000 + 1)
prime[0], prime[1] = False, False

for i in range(2, root + 1):
    if prime[i]:
        for j in range(i*2, 9001, i):
            prime[j] = False

answer = []
for arr in combinations(H, M):
    n = sum(arr)
    if prime[n]:
        answer.append(n)

print(*sorted(set(answer))) if len(answer) > 0 else print(-1)
N = 1_000_000
prime = [True] * (N+1)
prime[1] = False
root = int(N**0.5)
for i in range(2, root+1):
    if prime[i]:
        for j in range(i*2, N+1, i):
            prime[j] = False

def solution(n):
    answer = 0
    for i in range(1, n+1):
        if prime[i]:
            answer += 1
    return answer
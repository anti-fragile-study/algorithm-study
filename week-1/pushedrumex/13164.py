N, K = map(int, input().split())
tall = list(map(int, input().split()))

if N == K:
    print(0)
else:
    diff = []
    for i in range(1, N):
        diff.append(tall[i] - tall[i-1]) 
    diff.sort()

    print(sum(diff[:N-K]))

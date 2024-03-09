T = int(input())

for _ in range(T):
    N = int(input())
    arr = list(map(int, input().split()))

    mn, mx = min(arr), max(arr)
    print(mn, mx)
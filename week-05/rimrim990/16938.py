# 백준 16938 캠프 준비
n, l, r, x = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()
cnt = 0

def solve(low, high, acc):
    if acc > r:
        return

    if acc >= l and arr[high] - arr[low] >= x:
        global cnt
        cnt += 1

    for nxt in range(high + 1, n):
        solve(low, nxt, acc + arr[nxt])

for low in range(n-1):
    for high in range(low + 1, n):
        solve(low, high, arr[low] + arr[high])

print(cnt)
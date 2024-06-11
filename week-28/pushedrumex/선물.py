N, L, W, H = map(int, input().split())
left, right = 0, int(1e18)

L *= int(1e9)
W *= int(1e9)
H *= int(1e9)

while left <= right:
    mid = (left + right) // 2
    if (L // mid) * (W // mid) * (H // mid) >= N:
        answer = mid
        left = mid + 1
    else:
        right = mid - 1

print(answer * 10 ** (-9))
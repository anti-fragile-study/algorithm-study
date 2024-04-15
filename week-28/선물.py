N, L, W, H = map(lambda x: int(x) * (10 ** 9), input().split())
N = N // (10 ** 9)

MAX_A = min(L, W, H)
left, right = 0, MAX_A

answer = 0

while left <= right:
    a = (left + right) // 2;
    if (L // a) * (W // a) * (H // a) >= N:
        answer = max(answer, a)
        left = a + 1
    else:
        right = a - 1

print(answer / (10 ** 9))

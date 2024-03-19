N = int(input())
liqs= list(map(int, input().split()))
liqs.sort()

min_value = int(1e10)
answer = []
for i in range(N-2):
    a = liqs[i]
    point1, point2 = i+1, N-1

    while point1 < point2:
        b, c = liqs[point1], liqs[point2]
        s = a + b + c
        if min_value > abs(s):
            answer = [a, b, c]
            min_value = abs(s)

        if s == 0:
            answer = [a, b, c]
            break
        elif s > 0:
            point2 -= 1
        elif s < 0:
            point1 += 1

print(*sorted(answer))
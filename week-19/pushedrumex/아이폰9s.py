N = int(input())
arr = [int(input()) for _ in range(N)]

answer = 0
for n in arr:
    count = [0] * (1_000_000 + 1)
    last_num = None
    for i in range(N):
        m = arr[i]
        if n == m: continue
        if last_num != None:
            if last_num == m:
                count[m] += 1
            else:
                count[m] = 1
                last_num = m
        else:
            count[m] = 1
            last_num = m
        answer = max(answer, count[m])

print(answer)

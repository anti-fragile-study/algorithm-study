N, d, k, c = map(int, input().split())
arr = [int(input()) for _ in range(N)]

dic = [0] * (d+1)
answer = len(set(arr[:k] + [c]))
for i in range(k):
    dic[arr[i]] += 1

arr += arr
count = answer
for i in range(1, N+1):
    dic[arr[i-1]] -= 1
    if arr[i-1] != c and dic[arr[i-1]] == 0:
        count -= 1
    dic[arr[i+k-1]] += 1
    if arr[i+k-1] != c and dic[arr[i+k-1]] == 1:
        count += 1
    answer = max(answer, count)
    if answer == k + 1:
        break

print(answer)
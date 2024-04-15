N, K = map(int, input().split())
arr = list(map(int, input().split()))

count = [0] * (100_000 + 1)

answer = 1
left, right = 0, 1
count[arr[left]] += 1
while left < right < N:
    num = arr[right]
    count[num] += 1
    while count[num] > K:
        count[arr[left]] -= 1
        left += 1
    answer = max(answer, right - left + 1)
    right += 1
print(answer)
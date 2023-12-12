N = int(input())
arr = list(map(int, input().split()))

LIS = [arr[0]]

for n in arr:
    num = LIS[-1]
    if n > num: LIS.append(n)
    else:
        # n 을 포함했을 경우, 본인의 자리를 찾아 교환
        left, right = 0, len(LIS) - 1
        while left <= right:
            mid = (left + right) // 2
            if LIS[mid] < n:
                left = mid + 1
            else:
                result = mid
                right = mid - 1
        LIS[mid] = n

print(len(LIS))

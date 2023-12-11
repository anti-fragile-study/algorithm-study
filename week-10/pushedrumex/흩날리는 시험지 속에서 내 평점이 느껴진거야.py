N, K = map(int, input().split())
arr = list(map(int, input().split()))

answer = 0
left, right = 0, sum(arr)

while left <= right:
    mid = (left + right) // 2

    # mid 가 답이 되도록 그룹을 나눠보자
    group, score = 0, 0
    for n in arr:
        score += n
        # mid 가 답이 되려면 최솟값 >= mid 이어야함
        if score >= mid:
            group += 1
            score = 0
    # 가능성 있는 점수라면
    if group >= K:
        answer = mid
        left = mid + 1
    else:
        right = mid - 1

print(answer)

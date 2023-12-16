import sys
import itertools

n, l, r, x = map(int, sys.stdin.readline().split())
difficults = list(map(int, sys.stdin.readline().split()))

answer = 0
# 2개 ~ n개 선택 조합 모두 구하기
for count in range(2, n + 1):
    subsets = itertools.combinations(difficults, count)
    answer += sum(l <= sum(subset) <= r and max(subset) - min(subset) >= x for subset in subsets)

print(answer)

from collections import defaultdict

N = int(input())
source = list(map(int, input().split()))

M = int(input())
target = list(map(int, input().split()))

dic = defaultdict(bool)

for a in source:
    nums = [a]
    for b in dic.keys():
        nums.append(a + b)
        nums.append(abs(a - b))
    for num in nums:
        dic[num] = True

answer = []
for n in target:
    if dic[n]:
        answer.append("Y")
    else:
        answer.append("N")

print(*answer)
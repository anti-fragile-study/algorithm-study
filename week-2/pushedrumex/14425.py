from collections import defaultdict

N, M = map(int, input().split())

source = [input() for _ in range(N)]
target = [input() for _ in range(M)]

dic = defaultdict(bool)

for w in source:
    dic[w] = True

answer = 0

for w in target:
    if dic[w]:
        answer += 1
        
print(answer)
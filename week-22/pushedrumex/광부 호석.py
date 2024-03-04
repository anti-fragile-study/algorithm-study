from collections import deque
from collections import defaultdict

N, C = map(int, input().split())
stones = []
X, Y = 0, 0

for _ in range(N):
    x, y, v = map(int, input().split())
    X, Y = max(X, x), max(Y, y)
    stones.append((x, y, v))

xs = deque(sorted(stones, key=lambda x: (-x[0], -x[1], x[2])))
ys = deque(sorted(stones, key=lambda x: (-x[1], -x[0], x[2])))

c = N
while c > C:
    if xs[0][2] < ys[0][2]:
        xs.popleft()
        X = xs[0][0]
    else:
        ys.popleft()
        Y = ys[0][1]
    c -= 1
dic = defaultdict(bool)
for x in xs:
    dic[x] = True

answer = 0
for y in ys:
    if dic[y] == True:
        answer += y[2]
print(answer)

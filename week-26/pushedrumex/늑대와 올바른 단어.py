from collections import deque

q = deque(list(input()))

while q:
    w = 0
    while q and q[0] == "w":
        q.popleft()
        w += 1
    if w == 0:
        print(0)
        exit()
    answer = "o" * w + "l" * w + "f" * w
    target = ""
    count = 3 * w
    while q and count > 0:
        target += q.popleft()
        count -= 1
    if target != answer:
        print(0)
        exit()

print(1)
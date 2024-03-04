N = int(input())
h = list(map(int, input().split()))

S = sum(h)
k = S // 3
r = S % 3
if r != 0:
    print("NO")
else:
    max_two = 0
    for n in h:
        max_two += n // 2 # 2 단위로 뿌릴 수 있는 최대 일수
    if max_two < k:
        print("NO")
    else:
        print("YES")
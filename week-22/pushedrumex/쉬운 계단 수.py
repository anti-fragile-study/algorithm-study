N = int(input())
count = [0] + [1] * 9 # 맨뒤의 숫자가 각각 0 ~ 9일 경우 카운트
for _ in range(N-1):
    _count = [0] * 10
    for i in range(10):
        if count[i] > 0:
            if i > 0:
                _count[i-1] += count[i]
            if i < 9:
                _count[i+1] += count[i]
    count = _count
print(sum(count) % 1_000_000_000)

import sys


def solve():
    N, K = map(int, sys.stdin.readline().split())
    scores = list(map(int, sys.stdin.readline().split()))

    left = 0
    right = sum(scores)

    while left <= right:
        middle = (left + right) // 2
        acc = 0
        count = 0
        for score in scores:
            if acc + score < middle:
                acc += score
            else:
                acc = 0
                count += 1

        if count >= K:
            left = middle + 1
        else:
            right = middle - 1

    print(left - 1)


solve()

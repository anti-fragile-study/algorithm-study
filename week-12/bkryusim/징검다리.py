def check(distance, rocks, expected):
    acc = 0
    tokens = 0

    for i, rock in enumerate(rocks):
        if i == 0:
            acc += rock
        else:
            acc += rock - rocks[i - 1]

        if acc < expected:
            tokens += 1
        else:
            acc = 0

    acc += distance - rocks[-1]
    if acc < expected:
        tokens += 1

    return tokens


def solution(distance, rocks, n):
    left = 0
    right = distance
    rocks.sort()

    while left <= right:
        mid = (left + right) // 2
        tokens = check(distance, rocks, mid)

        if tokens > n:
            right = mid - 1
        else:
            left = mid + 1

    return right

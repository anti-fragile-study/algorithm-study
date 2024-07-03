def solution(k, tangerine):
    count = [0] * (10_000_000+1)
    for t in tangerine:
        count[t] += 1
    count.sort(reverse=True)
    i = 0
    while k > 0:
        k -= count[i]
        i += 1

    return i
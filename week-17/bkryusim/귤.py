from collections import defaultdict


def solution(k, tangerine):
    answer = 0
    d = defaultdict(int)

    for t in tangerine:
        d[t] += 1

    s = sorted(map(lambda x: (-d[x], x), d.keys()))

    for i in s:
        k += i[0]
        answer += 1
        if k <= 0:
            break

    return answer

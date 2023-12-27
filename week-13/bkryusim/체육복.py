def solution(n, lost, reserve):
    answer = n - len(lost)

    for l in lost:
        for r in reserve:
            if l == r:
                answer += 1
                lost.pop(lost.index(l))
                reserve.pop(reserve.index((r)))
                break

    lost.sort()
    reserve.sort()

    for l in lost:
        for i, r in enumerate(reserve):
            if r == l + 1 or r == l - 1 or r == l:
                reserve[i] = -1
                answer += 1
                break

    return answer

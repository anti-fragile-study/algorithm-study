def solution(N, stages):
    tries = [0] * (N + 2)
    success = [0] * (N + 2)

    for stage in stages:
        tries[stage] += 1
        success[stage - 1] += 1

    for i in range(N + 1, -1, -1):
        tries[i - 1] += tries[i]
        success[i - 1] += success[i]

    data = []
    for n in range(1, N + 1):
        if tries[n] != 0:
            data.append((success[n] / tries[n], n))
        else:
            data.append((1, n))
    data.sort()
    return list(map(lambda x: x[1], data))
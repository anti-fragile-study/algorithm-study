def solution(gems):
    answer = [0, 100001]
    d = dict()
    s = set()
    gems_num = len(set(gems))
    head = 0

    for tail in range(len(gems)):
        d[gems[tail]] = d.get(gems[tail], 0) + 1
        s.add(gems[tail])

        if len(s) == gems_num:
            for i in range(head, tail + 1):
                if d.get(gems[i]) == 1:
                    head = i
                    if tail - head < answer[1] - answer[0]:
                        answer = [head + 1, tail + 1]
                    break
                d[gems[i]] -= 1

    return answer

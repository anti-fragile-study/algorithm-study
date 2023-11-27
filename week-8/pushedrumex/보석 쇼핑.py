from collections import defaultdict

def solution(gems):
    dic = defaultdict(int)
    temp = 0
    N = len(gems)
    answer = [0, N-1]
    count = len(set(gems))
    start, end = 0, 0
    while end < N:
        # end 이동
        while end < N and temp < count:
            if dic[gems[end]] == 0:
                temp += 1
            dic[gems[end]] += 1
            end += 1

        # start 이동
        while temp == count:
            if dic[gems[start]] == 1:
                temp -= 1
            dic[gems[start]] -= 1
            start += 1
        
        if answer[1] - answer[0] > end - 1 - (start - 1):
            answer = [start - 1, end - 1]
        
    return [answer[0] + 1, answer[1] + 1]
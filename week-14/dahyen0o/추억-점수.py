from collections import defaultdict
from functools import reduce

def solution(name, yearning, photo):
    score_by_name = defaultdict(int)
    
    for n, y in zip(name, yearning):
        score_by_name[n] = y
    
    answer = []
    for ph in photo:
        answer.append(reduce(lambda x, y: x + score_by_name[y], ph, 0))
    return answer

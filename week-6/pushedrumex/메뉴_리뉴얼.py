from collections import defaultdict
from itertools import combinations

def solution(orders, course):
    dic = defaultdict(int)
    answer = []
    
    for n in course:
        dic.clear()
        for order in orders:
            if n > len(order): continue
            for temp in combinations(order, n):
                dic["".join(sorted(temp))] += 1
                
        max_count = 2
        temp_answer = []
        for k in dic.keys():
            if dic[k] == max_count:
                temp_answer.append(k)
            elif dic[k] > max_count:
                max_count = dic[k]
                temp_answer = [k]
        answer += temp_answer
        
    return sorted(answer)
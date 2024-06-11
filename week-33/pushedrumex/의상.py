from collections import defaultdict

def solution(clothes):
    dic = defaultdict(int)
    for _, type in clothes:
        dic[type] += 1
    answer = 1
    for type in dic.keys():
        answer *= (dic[type] + 1)
        
    return answer - 1
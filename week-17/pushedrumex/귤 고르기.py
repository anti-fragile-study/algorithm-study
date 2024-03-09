from collections import defaultdict

def solution(k, tangerine):
    dic = defaultdict(int)
    
    for t in tangerine:
        dic[t] += 1
        
    answer = 0
    for t in sorted(dic.keys(), key=lambda t: dic[t], reverse=True):
        k -= dic[t]
        answer += 1
        if k <= 0: break
        
    return answer
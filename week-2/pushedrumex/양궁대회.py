from itertools import combinations_with_replacement

max_diff_score = 0

def calculate_diff_score(a, l):
    a_score = 0
    l_score = 0
    
    for i in range(11):
        if l[i] + a[i] == 0: continue
        
        if l[i] > a[i]: l_score += 10 - i
        else: a_score += 10 - i
    
    return l_score - a_score

def solution(n, a_info):
    global max_diff_score

    answer = []
    for x in combinations_with_replacement(range(11), n):
        l_info = [0] * 11
        for i in x: l_info[i] += 1
        
        diff_score = calculate_diff_score(a_info, l_info)
        if diff_score > 0 and diff_score >= max_diff_score:
            if diff_score > max_diff_score:
                answer = l_info
            elif l_info[::-1] > answer[::-1]:
                answer = l_info
                
            max_diff_score = diff_score
    
    if len(answer) == 0: return [-1]
    return answer
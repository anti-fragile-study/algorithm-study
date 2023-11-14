from collections import defaultdict

dic = defaultdict(list)
def solution(info, query):
    answer = []
    for i in info:
        i = i.split(" ")
        score = int(i[-1])
        for a in (i[0], '-'):
            for b in (i[1], '-'):
                for c in (i[2], '-'):
                    for d in (i[3], '-'):
                        dic[(a,b,c,d)].append(score)
                        
    for k in dic.keys():dic[k].sort()
    
    # Klog(N) (K=100,000, N=50,000)
    for q in query:
        answer.append(bs(q))
        
    return answer

def bs(q):
    q = q.split(" and ")
    q = q[:-1] + q[-1].split(" ")
    score = int(q[-1])
    scores = dic[tuple(q[:-1])]
    left, right = 0, len(scores) - 1
    count = 0
    while left <= right:
        mid = (left + right) // 2
        if scores[mid] < score:
            left = mid + 1
        else:
            count = len(scores) - mid
            right = mid - 1
    return count
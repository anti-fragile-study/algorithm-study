from collections import defaultdict

def solution(id_list, report, k):
    reported = defaultdict(set)
    
    for r in report:
        fr, to = r.split()
        reported[to].add(fr)
    
    answer = defaultdict(int)
    
    for to, frs in reported.items():
        if len(frs) >= k:
            for fr in frs:
                answer[fr] += 1
        
    return [answer[id] for id in id_list]

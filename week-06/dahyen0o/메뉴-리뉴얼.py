import itertools
import collections

def solution(orders, courses):        
    answer = []
    
    for course in courses:
        count = collections.defaultdict(int)
        for order in orders:
            for comb in list(itertools.combinations(sorted(order), course)):
                count[''.join(comb)] += 1
        
        if not count:
            continue
            
        max_count = max(count.values())
        answer.extend(key for key, value in count.items() if value == max_count and max_count > 1)
    
    return sorted(answer)

from collections import defaultdict

def solution(N, stages):
    fail_rate = [0] * (N+1)
    stages_dic = defaultdict(int)
    for stage in stages:
        stages_dic[stage] += 1
    
    n = len(stages)
    for stage in range(1, N+1):
        if n != 0:
            fail_rate[stage] = stages_dic[stage] / n
            n -= stages_dic[stage]
    
    return sorted(list(range(1, N+1)), key=lambda x: (-fail_rate[x], x))
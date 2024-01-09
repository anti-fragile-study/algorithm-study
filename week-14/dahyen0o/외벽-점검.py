from itertools import combinations

def solution(N, weak, dist):
    dist.sort(reverse=True)
    
    intervals = [weak[0] + N - weak[-1]]
    for i in range(len(weak) - 1):
        intervals.append(weak[i + 1] - weak[i])
    
    for count in range(len(intervals) // 2 + 1):
        for passes in combinations(range(len(intervals)), count):
            result = 0
            for i in range(len(passes) - 1):
                if passes[i] + 1 == passes[i + 1]:
                    result += 1
            if passes and passes[-1] == len(intervals) - 1 and passes[0] == 0:
                result += 1
            
            # 친구들을 투입해야 하는 구간들 checks에 넣기
            curr_length = 0
            checks = []
            for i, interval in enumerate(intervals):
                if i in passes:
                    if curr_length > 0: checks.append(curr_length)
                    curr_length = 0
                else:
                    curr_length += interval
            if curr_length > 0: checks.append(curr_length)
            
            if 0 not in passes and len(intervals) - 1 not in passes and len(checks) > 1:
                # 마지막 부분과 처음 부분이 이어져 있는 경우 -> 처음 부분에 연결
                checks[0] += checks.pop()
            # 친구들을 투입할 수 있는 지 검사
            if len(checks) > len(dist): continue
            
            checks.sort(reverse=True)
            for c, d in zip(checks, dist):
                if c > d:
                    break
            else: 
                return result + len(checks)
    
    # 최후로 친구들이 취약 지점을 하나씩만 맡는 경우 (= 구간 필요 없음)
    if len(dist) >= len(weak): return len(weak)
    return -1

def is_passes_connected(size, passes):
    for i in range(size - 1):
        if i in passes and i + 1 in passes:
            return True
    if size - 1 in passes and 0 in passes:
        return True
    return False

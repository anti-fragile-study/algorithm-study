def solution(gems):
    counts = {}
    for gem in gems:
        counts[gem] = 0
        
    start, end = 0, 0
    answer = [0, 100_000_000]
    
    counts[gems[0]] += 1
    count = 1
    if count == len(counts) and (answer[1] - answer[0]) > (end - start):
        answer[0] = start
        answer[1] = end
    
    while True:
        if start == end or count < len(counts):
            end += 1
            
            if end == len(gems): break
            counts[gems[end]] += 1
            if counts[gems[end]] == 1:
                count += 1
        else:
            if (answer[1] - answer[0]) > (end - start):
                answer[0] = start
                answer[1] = end
            
            counts[gems[start]] -= 1
            if counts[gems[start]] == 0:
                count -= 1
            start += 1
    
    return list(map(lambda x: x + 1, answer))

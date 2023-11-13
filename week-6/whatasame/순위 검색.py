'''
효율성 테스트에서 시간 초과
'''

# 조건 없이 점수만 궁금해할 수 있다.

def solution(info, queries):
    # info: (k, v) => (조건, info_idx_set)
    # points: idx -> point
    info_map, points = to_map_and_points(info)
    
    answer = []
    # 모든 쿼리에 대하여 
    for query in queries:
        # 조건 파싱
        languages, point = parse(query)
        
        # 조건 교집합
        indices = intersection(info, info_map, languages)
        
        # 교집합 info_idx 돌면서 info의 점수 높은 개수 카운팅
        count = 0
        for idx in indices:
            if points[idx] >= point:
                count += 1
                
        answer.append(count)
        
    return answer
        
def to_map_and_points(info):
    info_map = {}
    points = []
    
    for idx, line in enumerate(info):
        words = line.split()
        
        conditions = words[:-1]
        points.append(int(words[-1]))
        
        for condition in conditions:
            if condition in info_map:
                info_map[condition].add(idx)
            else:
                indices = set()
                indices.add(idx)
                info_map[condition] = indices
                
    return info_map, points

def parse(query):
    conditions = [] 
    
    for q in query.split():
        if q == "-" or q == "and":
            continue
        conditions.append(q)
    
    languages = [language for language in conditions[:-1]]
    
    return languages, int(conditions[-1])
        
def intersection(info, info_map, languages):
    indices = set(range(len(info)))
    
    for language in languages:
        indices &= info_map[language]
                  
    return indices

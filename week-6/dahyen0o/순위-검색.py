from collections import defaultdict
from itertools import combinations, permutations

def solution(infos, query):
    info_score = defaultdict(list)
    
    for info in infos:
        lang, pos, career, food, score = info.split(" ")
        for l in (lang, '-'):
            for p in (pos, '-'):
                for c in (career, '-'):
                    for f in (food, '-'):
                        info_score[l + p + c + f].append(int(score))
    
    # 점수 리스트 정렬 (이분탐색을 위해)
    for key in info_score.keys():
        info_score[key].sort()
    
    answer = []
              
    for q in query:
        lang, pos, career, food_score = q.split(' and ')
        food, score = food_score.split(' ')
        
        key = lang + pos + career + food
        scores = info_score[key]
        score = int(score)
        
        # 이분탐색으로 score 이상인 점수의 개수 구하기
        left = 0
        right = len(scores) - 1

        while left <= right:
            mid = (left + right) // 2
            if scores[mid] >= score:
                right = mid - 1
            else:
                left = mid + 1
        answer.append(len(scores) - left)
        
    return answer

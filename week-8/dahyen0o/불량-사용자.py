from itertools import combinations, permutations
import re

def solution(user_ids, banned_ids):
    answer = 0
    banned_ids = list(map(lambda x: re.sub('\*', '.', x), banned_ids))
    
    for ids in combinations(user_ids, len(banned_ids)):
        for cases in permutations(ids, len(ids)):
            # banned_id를 만족하는 경우가 있는 지 검사
            if all(map(lambda idx : re.fullmatch(banned_ids[idx], cases[idx]), range(len(cases)))):
                answer += 1
                break

    return answer

# 제재 아이디 목록 경우의 수를 반환
# 순서 상관 x
# 불량 아이디가 전부 *일 수 있다.

# *를 a-z로 매핑하기 => 26^8 => 불가능

"""
["frodo", "fradi", "crodo"]
["fr*d*", "*rodo"]
-> 3
"""

from itertools import product


def solution(user_ids, banned_ids):
    candidates = [[] for _ in range(len(banned_ids))]

    for idx, banned_id in enumerate(banned_ids):
        for user_id in user_ids:
            if len(user_id) != len(banned_id):
                continue

            if match(user_id, banned_id):
                candidates[idx].append(user_id)

    answer = set()
    for case in product(*candidates):
        if len(set(case)) == len(banned_ids):
            answer.add(frozenset(case))

    return len(answer)


def match(user_id, banned_id):
    user_id = list(user_id)

    for idx in find_asterisk(banned_id):
        user_id[idx] = "*"

    return "".join(user_id) == banned_id


def find_asterisk(banned_id):
    return [idx for idx, char in enumerate(banned_id) if char == "*"]

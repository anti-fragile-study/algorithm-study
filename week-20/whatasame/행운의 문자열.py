# 행운의 문자열의 경우의 수를 반환

# 인접한 모든 문자가 같지 않다면 행운의 문자열

from itertools import permutations

s = input()

def solution(s):
    answer = set()

    # 모든 재배치 경우의 수에 대하여: 10! = 3,628,800
    for case in permutations(s):
        if lucky(case):
            answer.add("".join(case))

    return len(answer)

def lucky(string):
    # 행운의 문자열인지 확인: 10
    bef = string[0]
    for val in string[1:]:
        if bef == val:
            return False
        bef = val

    return True

print(solution(s))

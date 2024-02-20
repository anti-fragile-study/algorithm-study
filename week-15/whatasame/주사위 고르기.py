# 6면 주사위 n개 (1 ~ n)

# 승리만 따지자
# 가능한 주사위 조합 = 10C5 = 252
# 주사위 조합마다 가능한 주사위 눈 경우의 수 6^10 = 60,466,176

# A 승리 횟수가 가장 많은 경우 주사위 조합의 번호를 오름차순으로 반환한다.

from itertools import *

def solution(dices):
    n = len(dices)
    num_set = set(range(n))
    case_map = {}

    # 반반 나누는 모든 케이스에 대하여
    for a_case in combinations(range(n), n // 2):
        # A의 주사위 합의 경우의 수를 구한다.
        a_case = frozenset(a_case)
        a_dices = map(lambda x: dices[x], a_case)
        a_sum = [sum(case) for case in product(*a_dices)]

        # B의 주사위 합의 경우의 수를 구한다.
        b_case = num_set - a_case
        b_dices = map(lambda x: dices[x], b_case)
        b_sum = [sum(case) for case in product(*b_dices)]

        # A 주사위 합이 B 주사위 합보다 큰 경우를 투 포인터로 센다.
        a_sum, b_sum = sorted(a_sum), sorted(b_sum)
        cnt = 0
        pa, pb = 0, 0 # 투 포인터
        while pa < len(a_sum) and pb < len(b_sum):
            if a_sum[pa] > b_sum[pb]:
                cnt += len(a_sum) - pa
                pb += 1
            else:
                pa += 1

        # 케이스: 승리 수로 저장한다.
        case_map[a_case] = cnt

    # 승리수가 가장 많은 케이스를 오름차순으로 정렬해서 반환한다.
    answer = max(case_map.keys(), key = lambda key: case_map[key])

    return sorted(map(lambda val: val + 1, answer))

# 둘레 n미터, 둘레 중 일부 지점 취약
# 친구당 1시간 동안 이동할 수 있는 거리가 정해져있음
# 정북 0 -> 시계 방향 1, 2, 3, ...
# 점검 친구들은 cw or rcw 방향 이동

# 취약 지점을 점검하기 위한 친구 수의 최솟값

from collections import deque
from itertools import permutations

def solution(n, weaks, dists):
    answer = float("inf")

    # 친구 d명을 순서대로 세운다 -> O(d!)
    for case in permutations(dists):
        # 친구들을 투입해서 모두 점검했다면 해당 친구 수 리턴 -> O(weak * dist * weak)
        answer = min(answer, run(n, weaks, case))

    return answer if answer != float("inf") else -1

def run(n, weaks, dists):
    weaks = deque(weaks)

    # weaks를 원형으로 돌리면서 -> O(weak)
    friend_cnt = float("inf")
    for _ in range(len(weaks)):
        check_cnt = 0

        # 모든 친구들에 대하여 -> O(dist)
        for friend_idx, dist in enumerate(dists):
            # 가장 가까운 취약지점부터 갈 수 있는 곳까지 모두 점검 -> O(weak)
            end = weaks[check_cnt] + dist
            for idx in range(check_cnt, len(weaks)):
                if weaks[idx] <= end:
                    check_cnt += 1

            # 모두다 점검했다면 사용한 친구 수 업데이트
            if check_cnt == len(weaks):
                friend_cnt = min(friend_cnt, friend_idx + 1)
                break

        weaks.append(weaks.popleft() + n)

    return friend_cnt

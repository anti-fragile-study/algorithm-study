# A > B -> A가 B로부터 받는다
# A == B or null -> 선물지수가 큰 사람이 작은 사람으로부터 받는다.
# 선물 지수 마저 같다면 주고받지 않는다.
# 선물 지수 = 자신이 준 선물 - 받은 선물 (음수 가능)

# 다음 달에 가장 많은 선물을 받는 사람의 선물 수 반환

from collections import *

def solution(friends, gifts):
    friend_map = {friend: idx for idx, friend in enumerate(friends)}

    # {friend_idx: [준 선물 개수]}
    # [선물 지수] friend_idx
    gift_map = {idx: [0 for _ in range(len(friends))] for idx in range(len(friends))}
    gift_degree = [0 for _ in range(len(friends))]
    for gift in gifts:
        sender, receiver = map(lambda name : friend_map[name], gift.split())
        gift_map[sender][receiver] += 1
        gift_degree[sender] += 1
        gift_degree[receiver] -= 1

    # 서로에 대하여
    answer = [0 for _ in range(len(friends))]
    for sender in range(len(friends)):
        for receiver in range(len(friends)):
            if sender == receiver:
                continue

            # 준 선물 개수가 적으면 주기
            if gift_map[sender][receiver] < gift_map[receiver][sender]:
                answer[receiver] += 1
            # 준 선물이 같으면 선물 지수 비교
            elif gift_map[sender][receiver] == gift_map[receiver][sender]:
                # 선물 지수가 적으면 주기
                if gift_degree[sender] < gift_degree[receiver]:
                    answer[receiver] += 1

    return max(answer)


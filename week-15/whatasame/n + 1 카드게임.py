# 1 ~ n (n은 6의 배수) 사이의 카드 뭉치
# coin개의 동전

# 처음에 n / 3 개 뽑기
# 라운드 시작마다 2장 뽑기
# 카드 뭉치가 없다면 게임 종료!
# 동전을 소모해서 하나 가질 수 있음
# 두 장의 합이 n + 1이 되면 내고 다음 라운드로

"""
4, [1, 2, 10, 11, 12, 13]
-> 3
"""

from collections import *

n = None

def solution(coin, cards):
    global n
    n = len(cards)

    # n / 3 뽑기
    hands = set(cards[:n // 3])
    draws = set()
    cards = deque(cards[n // 3:])

    answer = 0
    # 카드 뭉치가 없을 때까지 반복
    while True:
        answer += 1
        if not cards:
            break

        # 두 장 드로우
        draws.add(cards.popleft())
        draws.add(cards.popleft())

        # 카드를 낼 수 있다면 다음 라운드로
        cost = play(hands, draws, coin)
        if cost != -1:
            coin -= cost
            continue

        # 어떻게 해도 낼 수 없다면 끝
        break

    return answer

def play(hands, draws, coin):
    global n

    # 코인 0개 사용
    for hand in hands:
        target = n + 1 - hand

        for other in hands:
            if hand == other:
                continue

            if other >= target:
                hands.remove(hand)
                hands.remove(other)

                return 0

    # 코인 1개 사용
    if coin < 1:
        return -1

    for hand in hands:
        target = n + 1 - hand

        for draw in draws:
            if draw >= target:
                hands.remove(hand)
                draws.remove(draw)

                coin -= 1
                return 1

    # 코인 2개 사용
    if coin < 2:
        return -1

    for draw in draws:
        target = n + 1 - draw

        for other in draws:
            if draw == other:
                continue

            if other >= target:
                draws.remove(draw)
                draws.remove(target)

                coin -= 2
                return 2

    return -1

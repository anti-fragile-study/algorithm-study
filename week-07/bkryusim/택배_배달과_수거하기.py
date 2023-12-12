import math


def solution(cap, n, deliveries, pickups):
    answer = 0
    unload = 0
    load = 0

    for home in range(n - 1, -1, -1):
        # 이번집 배송하고 채울 물품 양
        unload -= deliveries[home]
        # 이번집 싣고 채울 빈박스 양
        load -= pickups[home]
        # 이 집에 왕복한 횟수
        count = max(math.ceil(-unload / cap), math.ceil(-load / cap))

        # 왕복한 만큼 물건 채워
        unload += cap * count
        load += cap * count

        answer += count * (home + 1) * 2

    return answer

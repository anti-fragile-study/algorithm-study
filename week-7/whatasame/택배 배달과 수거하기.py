def solution(cap, n, deliveries, pickups):
    answer = 0
    remain_deli, remain_pick = 0, 0

    # 가장 먼 집부터 들려서
    for idx in range(n - 1, -1, -1):
        # 해당 집의 배달과 수거 양을 구한다.
        remain_deli += deliveries[idx]
        remain_pick += pickups[idx]

        # 해야할 배달과 수거가 있다면
        while remain_deli > 0 or remain_pick > 0:
            # 배달과 수거를 진행하고
            remain_deli -= cap
            remain_pick -= cap

            # 이동 거리를 합계
            distance = idx + 1
            answer += (distance * 2)

    return answer

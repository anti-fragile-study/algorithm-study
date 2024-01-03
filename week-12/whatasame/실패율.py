# 도달 클리어X / 도달

def solution(n, stages):
    # 도착, 클리어 배열 선언 (OOI 조심)
    arrived = [0 for _ in range(n + 2)]
    clear = [0 for _ in range(n + 2)]

    # stage 정보만 1명씩 추가
    for stage in stages:
        arrived[stage] += 1
        clear[stage - 1] += 1

    # idx층에 도착, 클리어한 사람은 idx - 1에도 도착, 클리어
    for idx in range(n + 1, 0, -1):
        arrived[idx - 1] += arrived[idx]
        clear[idx - 1] += clear[idx]

    # 성공률이 낮을수록 높은 실패율
    success = [(clear[idx] / arrived[idx], idx)
               if arrived[idx] != 0 else (1, idx)
               for idx in range(1, n + 1)]

    return list(map(lambda x : x[1], sorted(success)))



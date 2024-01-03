# rocks개의 바위 중 n개를 제거했을 때
# 출발 - 바위 - ... - 바위 - 도착 사이의 거리 중
# 최솟값이 가장 크게 될 때 해당 최솟값을 반환

def solution(distance, rocks, n):
    # 바위들 위치 정렬
    rocks.sort()

    # 구간 거리 리스트 생성
    section_dists = [rocks[idx + 1] - rocks[idx] for idx in range(len(rocks) - 1) ]
    section_dists = [rocks[0]] + section_dists + [distance - rocks[-1]]

    # 이분 탐색: 제거할 바위 개수가 n과 같을 때 middle = 최소 거리
    answer = None
    left, right = 1, distance
    while left <= right:
        middle = (left + right) // 2

        # 제거 바위 개수 구하기
        removed = calculate(section_dists, middle)

        # 제거바위 > n: 최소 거리 감소
        if removed > n:
            right = middle - 1

        # 제거바위 <= n: 최소 거리 증가, 최소 거리
        else:
            answer = middle
            left = middle + 1

    return answer

def calculate(section_dists, middle):
    dist, removed = 0, 0
    for section_dist in section_dists:
        dist += section_dist
        if dist >= middle:
            dist = 0
            continue

        removed += 1

    return removed

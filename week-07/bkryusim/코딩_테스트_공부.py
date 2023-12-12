import math
from collections import deque


def solution(alp, cop, problems):
    alp_target = alp
    cop_target = cop
    for problem in problems:
        if problem[0] > alp_target:
            alp_target = problem[0]
        if problem[1] > cop_target:
            cop_target = problem[1]

    if alp_target == alp and cop_target == cop:
        return 0

    queue = deque()

    dp = [[math.inf for _ in range(cop_target + 1)] for _ in range(alp_target + 1)]
    visited = [[False for _ in range(cop_target + 1)] for _ in range(alp_target + 1)]

    dp[alp][cop] = 0
    queue.append((alp, cop))
    visited[alp][cop] = True

    while queue:
        r, c = queue.popleft()

        # (row, column, cost)
        candidates = []

        # 오른쪽, 아래쪽 검사해서 큐에 넣고, cost 1로 이동할 수 있는 후보로 등록
        if r + 1 <= alp_target:
            candidates.append((r + 1, c, 1))
            if not visited[r + 1][c]:
                queue.append((r + 1, c))
                visited[r + 1][c] = True

        if c + 1 <= cop_target:
            candidates.append((r, c + 1, 1))
            if not visited[r][c + 1]:
                queue.append((r, c + 1))
                visited[r][c + 1] = True

        # 문제에 따라 풀 수 있다면 다음 좌표를 후보로 등록
        for problem in problems:
            if r >= problem[0] and c >= problem[1]:
                candidates.append((min(alp_target, r + problem[2]), min(cop_target, c + problem[3]), problem[4]))

        # 후보 위치의 값에 대소 비교 후 값 업데이트
        for candidate in candidates:
            if dp[candidate[0]][candidate[1]] > dp[r][c] + candidate[2]:
                dp[candidate[0]][candidate[1]] = dp[r][c] + candidate[2]

    return dp[alp_target][cop_target]

# 체스판: n x n

def solution(n):
    # idx -> r, val -> c 배열 선언
    pos = [0 for _ in range(n)]
    visited = [False for _ in range(n)]

    # 백 트래킹
    return dfs(pos, visited, 0, n)

def dfs(pos, visited, row, n):
    # 퀸을 끝까지 배치한 경우 -> 성공
    if row == n:
        return 1

    cnt = 0
    # 모든 열에 대하여
    for c in range(n):
        if visited[c]:
            continue

        pos[row] = c
        visited[c] = True
        # 퀸을 놓을 수 있다면
        if placable(pos, row):
            cnt += dfs(pos, visited, row + 1, n)
        visited[c] = False

    return cnt

def placable(pos, row):
    # row행보다 이전 행에 대하여
    for bef in range(row):
        # 세로, 대각선 검증
        if pos[row] == pos[bef] or row - bef == abs(pos[row] - pos[bef]):
            return False

    return True

# 한 번에 하나씩
# 회전 가능
# 한 번 넣을 때, 딱 맞게 들어가야 함
# 0: 빈칸, 1: 조각

# 최대한 많이 넣는 경우 채운 칸 수를 반환

from collections import *
from copy import *

directions = [(0, 1), (0, -1), (1, 0), (-1, 0)]

def solution(board, table):
    # puzzle:blank = 1:n 관계

    answer = 0
    # 빈 칸들 = [빈 칸 퍼즐]
    blanks = [nomalize(blank) for blank in find_block(board, 0)]

    # 4번 돌리면서
    for _ in range(4):
        # 퍼즐 모두 구하기
        for puzzle in find_block(table, 1):
            nomalized_puzzle = nomalize(puzzle)

            for idx, nomalized_blank in enumerate(blanks):
                if nomalized_puzzle != nomalized_blank:
                    continue

                # 채우기
                blanks.pop(idx)
                answer += len(puzzle)
                for r, c in puzzle:
                    table[r][c] = 0
                break

        table = rotate(table)

    return answer

def find_block(arr, val):
    ret = []
    n = len(arr)
    visited = [[False for _ in range(n)] for _ in range(n)]

    def dfs(r, c):
        route = [(r, c)]

        q = deque([(r, c)])
        visited[r][c] = True
        while q:
            r, c = q.popleft();

            for dr, dc in directions:
                nr, nc = r + dr, c + dc

                if not 0 <= nr < n or not 0 <= nc < n or visited[nr][nc] or arr[nr][nc] != val:
                    continue

                route.append((nr, nc))
                q.append((nr, nc))
                visited[nr][nc] = True

        return route

    for sr in range(n):
        for sc in range(n):
            if arr[sr][sc] != val or visited[sr][sc]:
                continue


            ret.append(dfs(sr, sc))

    return ret

def nomalize(block):
    br, bc = block[0] # base idx

    return {(r - br, c - bc) for r, c in block}

def rotate(arr):
    return [list(reversed(row)) for row in zip(*arr)]

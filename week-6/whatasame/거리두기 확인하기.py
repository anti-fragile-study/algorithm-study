# P: 응시자
# O: 빈 테이블
# X: 파티션
# 거리두기 ok -> 1, no -> 0

from collections import deque

def solution(places):
    answer = []
    
    # 모든 대기실에 대하여
    for place in places:
        answer.append(check(place))
            
    return answer

def check(place):
    row_len, col_len = len(place), len(place[0])
    
    # 각 칸에 대하여
    for r in range(row_len):
        for c in range(col_len):
            # 사람이면 BFS(상, 하, 좌, 우)로 가장 가까운 사람의 거리 찾기
            # 거리가 2 이하이면 거리두기 X (거리 0 == 사람 없음)
            if place[r][c] == "P" and 1 <= bfs(place, r, c) <= 2:
                    return 0
    
    # 거리두기 O
    return 1
        
def bfs(place, row, col):
    row_len, col_len = len(place), len(place[0])
    directions = [(1, 0), (-1, 0), (0, 1), (0, -1)]
    
    visited = [[False for _ in range(col_len)] for _ in range(row_len)]
    queue = deque()
    queue.append((row, col, 0)) # distance
    visited[row][col] = True
    
    while queue:
        row, col, distance = queue.popleft()
        
        for dr, dc in directions:
            nr, nc = row + dr, col + dc
            
            if 0 <= nr < row_len and 0 <= nc < col_len and visited[nr][nc] == False:
                if place[nr][nc] == "P":
                    return distance + 1
                
                if place[nr][nc] == "X":
                    visited[row][col] = True
                    
                if place[nr][nc] == "O":
                    queue.append((nr, nc, distance + 1))
                    visited[row][col] = True
    
    # 사람 못 찾으면 0 반환    
    return 0
        
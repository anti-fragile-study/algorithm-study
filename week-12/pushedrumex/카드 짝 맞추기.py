from collections import deque, defaultdict

SIZE = 4
point_dic = defaultdict(list)
board = None
card_dic = [False] * 7
answer = int(1e9)
def solution(_board, r, c):
    global board
    board = _board
    for i in range(SIZE):
        for j in range(SIZE):
            n = board[i][j]
            if n != 0:
                card_dic[n] = True
                point_dic[n].append((i, j))

    dfs((r, c), 0)
    return answer

def dfs(now, cnt):
    global answer

    for card in point_dic.keys():
        if card_dic[card]:
            card_dic[card] = False
            x1, y1 = point_dic[card][0]
            x2, y2 = point_dic[card][1]

            # now -> 1 -> 2
            temp = bfs(now, (x1, y1))
            board[x1][y1] = 0
            temp += bfs((x1, y1), (x2, y2))
            board[x2][y2] = 0
            
            dfs((x2, y2), cnt + temp + 2)
            
            board[x1][y1] = card
            board[x2][y2] = card
            
            # now -> 2 -> 1
            temp = bfs(now, (x2, y2))
            board[x2][y2] = 0
            temp += bfs((x2, y2), (x1, y1))
            board[x1][y1] = 0
            
            dfs((x1, y1), cnt + temp + 2)
            
            board[x1][y1] = card
            board[x2][y2] = card

            card_dic[card] = True
    
    if sum(card_dic) == 0:
        answer = min(answer, cnt)

 # 목적지까지의 최소 비용
dxdy = ((0,1),(1,0),(0,-1),(-1,0))
def bfs(start, end):
    q = deque([(*start, 0)])
    visited = [[False] * SIZE for _ in range(SIZE)]
    x, y = start
    visited[x][y] = True
    while q:
        x, y, cnt = q.popleft()
        
        if (x, y) == (end[0], end[1]):
            return cnt
    
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            if not (0 <= _x < SIZE and 0 <= _y < SIZE) or visited[_x][_y]: continue
            q.append((_x, _y, cnt + 1))
            visited[_x][_y] = True
        # ctrl
        for dx, dy in dxdy:
            _x, _y = x + dx, y + dy
            while 0 <= _x < SIZE and 0 <= _y < SIZE and board[_x][_y] == 0:
                _x += dx
                _y += dy
            if not (0 <= _x < SIZE and 0 <= _y < SIZE):
                _x -= dx
                _y -= dy
            if not visited[_x][_y]:
                q.append((_x, _y, cnt + 1))
                visited[_x][_y] = True
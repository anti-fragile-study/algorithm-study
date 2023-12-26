from itertools import permutations
from copy import deepcopy
from collections import deque, defaultdict

cards_coord = defaultdict(list)

def solution(board, r, c):
    # 카드 종류 구하기
    max_card = 0
    for i in range(4):
        for j in range(4):
            max_card = max(max_card, board[i][j])
            if board[i][j] > 0:
                cards_coord[board[i][j]].append((i, j))
    
    answer = 1_000_000_000
    # 맞출 카드 순서를 정해 게임 플레이
    for cards in permutations(range(1, max_card + 1)):
        # print(cards, play(board, r, c, cards))
        answer = min(answer, play(board, r, c, cards))
    return answer

def play(board, r, c, cards):
    game_count = 0
    board = deepcopy(board)
    for card in cards:
        # 현재 위치에서 card 두 개를 제거
        r, c, count = open_cards(board, card, r, c)
        game_count += count + 2 # enter 키 입력 2번 추가
        print(cards, count + 2, "위치", r, c)
    return game_count
        
def open_cards(board, card, r, c):
    # BFS로 두 card를 없애는 최단 경로 탐색    
    visited = [[[False for _ in range(3)] for _ in range(4)] for _ in range(4)]
    visited[r][c][0] = True # row, col, 카드를 연 횟수
    queue = deque([(r, c, 0, 0)]) # row, col, 카드를 연 횟수, 키 조작 횟수
    if board[r][c] == card:
        visited[r][c][cards_coord[card].index((r, c)) + 1] = True
        queue.popleft()
        queue.append((r, c, cards_coord[card].index((r, c)) + 1, 1))
    
    while queue:
        curr = queue.popleft()
        
        # if board[curr[0]][curr[1]] == card: # 현재 카드가 card임
        #     print(card, curr)
            
        if curr[2] == 3: # 두 개의 카드를 열었음
            # 맞춘 카드들 없애기
            # print(card, card_coord)
            for coord in cards_coord[card]:
                board[coord[0]][coord[1]] = 0
            return (curr[0], curr[1], curr[3])
        
        # 그냥 이동
        for dir in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
            r = curr[0] + dir[0]
            c = curr[1] + dir[1]
            if not can_move(r, c) or visited[r][c][curr[2]]: continue
            next = (r, c, curr[2], curr[3] + 1)
            if board[r][c] == card:
                next = (r, c, curr[2] + cards_coord[card].index((r, c)) + 1, curr[3] + 1)
                visited[r][c][cards_coord[card].index((r, c)) + 1] = True
                # print((r,c), cards_coord[card].index((r, c)) + 1)
            visited[r][c][curr[2]] = True
            queue.append(next)
        
        # ctrl 이동
        for dir in [(0, 1), (1, 0), (0, -1), (-1, 0)]:
            r = curr[0] + dir[0]
            c = curr[1] + dir[1]
            while can_move(r, c) and board[r][c] == 0:
                r += dir[0]
                c += dir[1]
            if not can_move(r, c):
                r -= dir[0]
                c -= dir[1]
            if visited[r][c][curr[2]]: continue
            next = (r, c, curr[2], curr[3] + 1)
            if board[r][c] == card:
                next = (r, c, curr[2] + cards_coord[card].index((r, c)) + 1, curr[3] + 1)
                visited[r][c][cards_coord[card].index((r, c)) + 1] = True
                # print((r,c), cards_coord[card].index((r, c)) + 1)
            visited[r][c][curr[2]] = True
            queue.append(next)
            

def can_move(r, c):
    return r >= 0 and c >= 0 and r < 4 and c < 4
        

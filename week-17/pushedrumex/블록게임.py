# 일단 위쪽에 비어있는 부분들을 검은 블록으로 꽉 채움
# 꽉착 직사각형이 있는 지 탐색
# 있다면 제거하고 1번 부터 재시작
# 없다면 끝
board = None
N, M = None, None
def solution(_board):
    global board, N, M
    board = _board
    
    N, M = len(board), len(board[0])
    
    answer = 0
    flag = True
    while flag:
        flag = False
        for j in range(M):
            for i in range(N):
                if board[i][j] > 0: break
                board[i][j] = -1 # 검은 블록

        for i in range(N):
            for j in range(M):
                if isMatch(i, j, 2, 3):
                    remove(i, j, 2, 3)
                    flag = True
                    answer += 1
                elif isMatch(i, j, 3, 2):
                    remove(i, j, 3, 2)
                    flag = True
                    answer += 1
        
    return answer

def isMatch(x, y, x_size, y_size):
    num = None
    block = 0
    for i in range(x, x+x_size):
        for j in range(y, y+y_size):
            if oob(i, j) or board[i][j] == 0: return False
            if board[i][j] == -1: continue
            block += 1
            if num == None:
                num = board[i][j]
            elif num != board[i][j]:
                    return False

    return block == 4

def remove(x, y, x_size, y_size):
    for i in range(x, x+x_size):
        for j in range(y, y+y_size):
            board[i][j] = 0

def oob(x, y):
    return not (0 <= x < N and 0 <= y < M)
    
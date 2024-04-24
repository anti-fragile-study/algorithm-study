import sys
input = sys.stdin.readline

R, C = map(int, input().split())
arr = [list(input()) for _ in range(R)]
N = int(input())

def move(row, col):
    # 돌을 바닥 또는 돌이 있는 곳까지 보내기
    # O(R)
    while row < R and arr[row][col] == ".":
        row += 1

    row -= 1
    
    if row == R-1 or arr[row+1][col] == "X":
        return (row, col)

    # 왼쪽 아래 또는 오른쪽 아래가 비어있다면 미끄러지기
    if col > 0 and arr[row+1][col-1] == ".":
        row += 1
        col -= 1
    elif col < C-1 and arr[row+1][col+1] == ".":
        row += 1
        col += 1

    return (row, col)

for _ in range(N):
    col = int(input()) - 1

    pre_row, pre_col = 0, col
    while True:
        row, col = move(pre_row, pre_col)
        if (row, col) == (pre_row, pre_col):
            arr[row][col] = "O"
            break
        else:
            pre_row, pre_col = row, col

for row in arr:
    print("".join(row))
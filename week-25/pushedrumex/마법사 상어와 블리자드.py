from collections import deque

N, M = map(int, input().split())

arr = [list(map(int, input().split())) for _ in range(N)]
direc = {1: (-1, 0), 2: (1, 0), 3: (0, -1), 4: (0, 1)}

bomb = [0] * 4

# 얼음 투척
def ice(d, s):
    x, y = N // 2, N // 2
    dx, dy = direc[d]
    for _ in range(s): 
        x += dx
        y += dy
        if arr[x][y] > 0:
            arr[x][y] = 0

def move():
    global arr

    # 구슬 순서대로 넣기
    nums = deque()

    # 구슬 이동
    for i in range(N // 2):
        x, y = N // 2 - 1 - i, N // 2 - 1 - i
        size = 2 * (i + 1)
        
        # 왼쪽
        dx, dy = 1, 0
        for _ in range(size):
            x += dx
            y += dy
            if arr[x][y] > 0:
                nums.append(arr[x][y])
        
        # 아래
        dx, dy = 0, 1
        for _ in range(size):
            x += dx
            y += dy
            if arr[x][y] > 0:
                nums.append(arr[x][y])
        
        # 오른쪽
        dx, dy = -1, 0
        for _ in range(size):
            x += dx
            y += dy
            if arr[x][y] > 0:
                nums.append(arr[x][y])
        
        # 위
        dx, dy = 0, -1
        for _ in range(size):
            x += dx
            y += dy
            if arr[x][y] > 0:
                nums.append(arr[x][y])
    
    # 구슬 폭발
    bomb_nums = []
    while True:
        flag = False
        while nums:
            num = nums.popleft()
            seq = 1
            while nums and nums[0] == num:
                seq += 1
                nums.popleft()
            if seq < 4:
                bomb_nums += [num] * seq
            else:
                bomb[num] += seq
                flag = True

        if not flag:
            break

        nums = deque(bomb_nums[:])
        bomb_nums = []

    # 구슬 변화
    change_nums = deque()
    bomb_nums = deque(bomb_nums)
    while bomb_nums:
        num = bomb_nums.popleft()
        count = 1
        while bomb_nums and bomb_nums[0] == num:
            count += 1
            bomb_nums.popleft()
        change_nums += [count, num]

    # 격자에 다시 넣기
    arr = [[0] * N for _ in range(N)]
    k = 0
    change_nums += [0] * (N*N)
    for i in range(N // 2):
        x, y = N // 2 - 1 - i, N // 2 - 1 - i
        size = 2 * (i + 1)
        
        # 왼쪽
        dx, dy = 1, 0
        for _ in range(size):
            x += dx
            y += dy
            arr[x][y] = change_nums[k]
            k += 1
        
        # 아래
        dx, dy = 0, 1
        for _ in range(size):
            x += dx
            y += dy
            arr[x][y] = change_nums[k]
            k += 1
        
        # 오른쪽
        dx, dy = -1, 0
        for _ in range(size):
            x += dx
            y += dy
            arr[x][y] = change_nums[k]
            k += 1
        
        # 위
        dx, dy = 0, -1
        for _ in range(size):
            x += dx
            y += dy
            arr[x][y] = change_nums[k]
            k += 1

for _ in range(M):
    d, s = map(int, input().split())
    ice(d, s)
    move()

print(1*bomb[1]+2*bomb[2]+3*bomb[3])

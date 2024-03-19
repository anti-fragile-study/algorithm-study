dic = {}
빙고판 = [list(map(int, input().split())) for _ in range(5)]
for i in range(5):
    for j in range(5):
        dic[빙고판[i][j]] = (i, j)

nums = []
for _ in range(5):
    nums += list(map(int, input().split()))

def 빙고():
    count = 0

    # 행 확인
    for row in 빙고판:
        if sum(row) == 0:
            count += 1
    
    # 열 확인
    for j in range(5):
        temp = 0
        for i in range(5):
            temp += 빙고판[i][j]
        if temp == 0:
            count += 1
    
    # 대각선 확인
    temp = 0
    x, y = 0, 0
    for i in range(5):
        temp += 빙고판[x+i][y+i]
    if temp == 0:
        count += 1

    temp = 0
    x, y = 0, 4
    for i in range(5):
        temp += 빙고판[x+i][y-i]
    if temp == 0:
        count += 1
    
    return count >= 3

for i in range(25):
    x, y = dic[nums[i]]
    빙고판[x][y] = 0

    if 빙고():
        print(i+1)
        break
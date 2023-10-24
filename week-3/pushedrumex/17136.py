paper = [list(map(int, input().split())) for _ in range(10)]

sizes = list(range(1, 6))
dic = {}
for size in sizes: dic[size] = 0

answer = int(1e9)
def dfs(count):
    global answer

    for i in range(10):
        for j in range(10):
            if paper[i][j] == 1:
                for size in sizes:
                    flag = True
                    for x in range(i, i+size):
                        for y in range(j, j+size):
                            if not (0 <= x < 10 and 0 <= y < 10) or paper[x][y] == 0:
                                flag = False
                    # 덮는게 가능하다면
                    if flag and dic[size] < 5:
                        dic[size] += 1
                        for x in range(i, i+size):
                            for y in range(j, j+size):
                                paper[x][y] = 0

                        dfs(count + 1)

                        dic[size] -= 1
                        for x in range(i, i+size):
                            for y in range(j, j+size):
                                paper[x][y] = 1
                return
    answer = min(answer, count)

dfs(0)

print(answer) if answer != int(1e9) else print(-1)
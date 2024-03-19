# 계란의 내구도는 상대 계란의 무게만큼 깎임

answer = 0
N = int(input())
eggs = [list(map(int, input().split())) for _ in range(N)]

def dfs(i, count):
    global answer
    answer = max(answer, count)

    if i >= N:
        return
    
    if eggs[i][0] <= 0:
        dfs(i+1, count)
        return
    
    for j in range(N):
        if i == j: continue

        s, w = eggs[j]
        if s <= 0:
            continue

        eggs[i][0] -= w
        eggs[j][0] -= eggs[i][1]

        broken = 0
        if eggs[i][0] <= 0:
            broken += 1
        if eggs[j][0] <= 0:
            broken += 1

        dfs(i+1, count + broken)

        eggs[i][0] += w
        eggs[j][0] += eggs[i][1]

dfs(0, 0)

print(answer)
answer = 0
def dfs(i, score):
    global answer

    if i == 11:
        answer = max(answer, score)
        return
    
    for j in range(11):
        if not visited[j] and arr[j][i] > 0:
            visited[j] = True
            dfs(i+1, score + arr[j][i])
            visited[j] = False

C = int(input())
for _ in range(C):
    answer = 0
    arr = [list(map(int, input().split())) for _ in range(11)]
    visited = [False] * 11
    dfs(0, 0)
    print(answer)
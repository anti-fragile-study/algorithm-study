from collections import defaultdict

arr = list(input())
N = len(arr)
dic = defaultdict(bool)
answer = 0

visited = [False] * N
def dfs(s):
    global answer

    if len(s) == N:
        if not dic[s]:
            answer += 1
            dic[s] = True

        return
    
    for i in range(N):
        if not visited[i]:
            if len(s) == 0 or s[-1] != arr[i]:
                visited[i] = True
                dfs(s + arr[i])
                visited[i] = False

dfs("")
print(answer)
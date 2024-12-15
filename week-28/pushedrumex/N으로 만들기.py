from collections import defaultdict

N = input()
l = len(N)

if l == 1:
    print(1)
    exit()
    
visited = defaultdict(bool)
answer = 0
def dfs(order, left, right):
    global answer

    if right - left + 1 == l:
        if not visited[order]:
            answer += 1
            visited[order] = True
        return
    
    if left > 0:
        dfs(order + N[left-1] + order, left-1, right)
    if right < l-1:
        dfs(order + order + N[right+1], left, right+1)

for i in range(l-1):
    dfs(N[i]+N[i+1], i, i+1)
    dfs(N[i+1]+N[i], i, i+1)

print(answer)
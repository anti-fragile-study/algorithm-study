import sys
sys.setrecursionlimit(10**9)

N = int(input())
graph = [[] for _ in range(N+1)]
parents = [0, 0] + list(map(int, input().split()))
for child in range(2, N+1):
    parent = parents[child]
    graph[parent].append(child)

skill = [0] + list(map(int, input().split()))

visited = [False] * (N+1)
result = 0
def dfs(now, value):
    global result
    result = max(value, result)
    
    for parent in range(now, N+1):
        if visited[parent]: continue
        visited[parent] = True
        for child in graph[parent]:
            if visited[child]: continue
            visited[child] = True
            dfs(now+1, value + skill[parent] * skill[child])
            visited[child] = False
        visited[parent] = False

dfs(1, 0)

print(result)
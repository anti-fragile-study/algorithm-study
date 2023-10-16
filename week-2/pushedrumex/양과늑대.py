def solution(info, edges):
    answer = 1
    visited = [False for _ in range(17)]    
    
    def dfs(sheep, wolf):
        nonlocal answer
        if sheep == wolf: return
        answer = max(answer, sheep)
        
        for parent, child in edges:
            if visited[parent] and not visited[child]:
                visited[child] = True
                if info[child] == 1:
                    dfs(sheep, wolf + 1)
                else:
                    dfs(sheep + 1, wolf)
                visited[child] = False
        
    visited[0] = True
    dfs(1, 0)
    return answer
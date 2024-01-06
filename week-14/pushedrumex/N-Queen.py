n = None
answer = 0
def solution(_n):
    global n
    n = _n
    dfs([-1] * n, 0)
    return answer

visited = [False] * 12
def dfs(nums, col):
    global answer
    if col >= n:
        answer += 1
        return
    for i in range(n):
        if not visited[i]:
            visited[i] = True
            nums[col] = i
            if is_possible(nums, col):
                dfs(nums, col + 1)
            visited[i] = False
            
def is_possible(nums, col):
    for i in range(col):
        if abs((nums[i] - nums[col]) / (i - col)) == 1:
            return False
    return True

def solution(alp, cop, problems):
    alp_goal, cop_goal = 0, 0
    for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems:
        alp_goal = max(alp_goal, alp_req)
        cop_goal = max(cop_goal, cop_req)
    
    dp = [[int(1e9)] * (cop_goal+2) for _ in range(alp_goal+2)]
    alp, cop = min(alp, alp_goal), min(cop, cop_goal)
    dp[alp][cop] = 0
    
    for i in range(alp, alp_goal+1):
        for j in range(cop, cop_goal+1):
            dp[i+1][j] = min(dp[i+1][j], dp[i][j]+1)
            dp[i][j+1] = min(dp[i][j+1], dp[i][j]+1)
            
            for alp_req, cop_req, alp_rwd, cop_rwd, cost in problems: 
                if alp_req <= i and cop_req <= j:
                    x = min(alp_goal, i+alp_rwd)
                    y = min(cop_goal, j+cop_rwd)
                    dp[x][y] = min(dp[x][y], dp[i][j] + cost)
    
    return dp[alp_goal][cop_goal]
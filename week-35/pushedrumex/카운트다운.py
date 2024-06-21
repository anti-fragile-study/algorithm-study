def solution(target):
    # 다트 수, 싱글 또는 불
    dp = [(int(1e9), 0) for _ in range(100_000 + 1)]
    for i in range(1, 20+1):
        dp[i] = (1, -1)
        dp[i*2] = min(dp[i*2], (1, 0))
        dp[i*3] = min(dp[i*3], (1, 0))
    dp[50] = (1, -1)
    
    for i in range(1, target+1):
        for j in range(1, 61):
            if j > i: break
            dp[i] = min(dp[i], (dp[i-j][0]+dp[j][0], dp[i-j][1]+dp[j][1]))

    return [dp[target][0], -dp[target][1]]
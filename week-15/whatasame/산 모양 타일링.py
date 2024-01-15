# 경우의 수를 10,007로 나눈 나머지를 반환

def solution(n, tops):
    dp = [0 for _ in range(2 * n + 1 + 1)]
    dp[0] = 1
    dp[1] = 1

    for idx in range(2, 2 * n + 1 + 1):
        dp[idx] = dp[idx - 1] + dp[idx - 2]
        dp[idx] %= 10_007

        # 역삼각형이 추가될 때
        if idx % 2 == 0:
            # 역삼각형 자리에 뾰족이면 한 한번 더 추가
            if tops[idx // 2 - 1] == 1:
                dp[idx] += dp[idx -1]
                dp[idx] %= 10_007

    return dp[2 * n + 1]

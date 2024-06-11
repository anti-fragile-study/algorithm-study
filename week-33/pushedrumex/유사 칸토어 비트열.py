seg = [0, 1]
def solution(n, l, r):
    for _ in range(n-1):
        seg.append(seg[-1] * 4)
    return dfs(r, n) - dfs(l-1, n)

def dfs(i, n):
    if n == 0:
        return 0
    bit = 5 ** (n-1)
    a = i // bit
    # 몫이 2라면, 나머지 비트는 0인 비트
    if a == 2:
        return a * seg[n]
    # 몫이 2 초과라면, 0인 비트 구간 제외
    if a > 2:
        a -= 1
    # 몫 * 비트 구간 합 + 나머지 비트 계산
    return a * seg[n] + dfs(i % bit, n-1)
    
import sys

input = lambda:sys.stdin.readline().strip()

n = int(input())

def solution(n):
    min_cnt, max_cnt = float("inf"), 0

    def dfs(n, cnt):
        nonlocal min_cnt, max_cnt

        if n < 10:
            if n % 2 == 1:
                cnt += 1
            min_cnt, max_cnt = min(min_cnt, cnt), max(max_cnt, cnt)
        elif 10 <= n < 100:
            nxt = n // 10 + n % 10
            dfs(nxt, cnt + count(n))
        else:
            length = len(str(n))
            for i in range(1, length - 1):
                for j in range(i + 1, length):
                    string = str(n)
                    splited = [string[:i], string[i:j], string[j:]]
                    nxt = sum(map(int, splited))

                    dfs(nxt, cnt + count(n))

    dfs(n, 0)

    return min_cnt, max_cnt

def count(num):
    return len([c for c in str(num) if int(c) % 2 == 1])

print(*solution(n))

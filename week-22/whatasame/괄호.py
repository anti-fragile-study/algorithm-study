import sys

input = sys.stdin.readline
n = int(input())
lines = [input().strip() for _ in range(n)]

def solution(lines):
    answer = []

    for line in lines:
        stack = []

        for p in line: # parenthesis
            if not stack:
                stack.append(p)
                continue

            if p == ")" and stack[-1] == "(":
                stack.pop()
                continue

            stack.append(p)

        if stack:
            answer.append("NO")
        else:
            answer.append("YES")

    return answer

print("\n".join(solution(lines)))

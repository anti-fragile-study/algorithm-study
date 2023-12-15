# 괄호 개수는 맞지만 짝이 안 맞음

# ( )의 개수가 같다면 균형잡힌
# ( )의 짝이 같다면 올바른

def solution(p):
    return convert(p)


def convert(p):
    # 1번
    if not p:
        return p

    # 2번
    u, v = split(p)

    # 3번
    if correct(u):
        v = convert(v)

        # 3-1번
        return u + v

    # 4번
    return "(" + convert(v) + ")" + reverse(u)


def split(p):
    open_cnt, close_cnt = 0, 0

    for idx, char in enumerate(p):
        if char == "(":
            open_cnt += 1
        if char == ")":
            close_cnt += 1
        if open_cnt == close_cnt:
            return p[:idx + 1], p[idx + 1:]


def correct(p):
    stack = []

    for char in p:
        peek = stack[-1] if stack else ""

        if peek + char == "()":
            stack.pop()
        else:
            stack.append(char)

    return len(stack) == 0


def reverse(p):
    return "".join([")" if char == "(" else "(" for char in p[1:-1]])

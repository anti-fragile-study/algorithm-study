def check(p):
    count = 0

    for c in list(p):
        if c == '(':
            count += 1
        if c == ')':
            count -= 1

        if count < 0:
            return False
    if count != 0:
        return False

    return True


def inverse(p):
    result = ''
    for c in list(p):
        if c == '(':
            result += ')'
        if c == ')':
            result += '('
    return result


def solution(p):
    if p == '':
        return ''

    open = 0
    close = 0

    # u, v 분리
    index = 0
    while (open == 0 and close == 0) or open != close:
        if p[index] == '(':
            open += 1
        if p[index] == ')':
            close += 1
        index += 1

    u = p[:index]
    v = p[index:]

    if check(u):
        return u + solution(v)

    return '(' + solution(v) + ')' + inverse(u[1:len(u) - 1])

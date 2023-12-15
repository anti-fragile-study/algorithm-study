def solution(p):             
    return translate(p)

def translate(p):
    if not p:
        return ''
    if is_right(p):
        return p
    
    idx = find_v_idx(p)
    u = p[:idx + 1]
    v = p[idx + 1:]

    if is_right(u):
        return u + translate(v)
    
    return '(' + translate(v) + ')' + reverse(u[1:-1])

def find_v_idx(p):
    count = [0, 0]
    for idx in range(len(p)):
        if p[idx] == '(':
            count[0] += 1
        else:
            count[1] += 1
        if count[0] == count[1] and count[0] != 0:
            return idx
    return len(p) - 1

def is_right(p):
    stack = []
    for ch in p:
        if ch == '(':
            stack.append(ch)
        else:
            if stack:
                stack.pop()
            else:
                return False
    return not stack
    
def reverse(p):
    result = ''
    for ch in p:
        if ch == '(':
            result += ')'
        else:
            result += '('
    return result

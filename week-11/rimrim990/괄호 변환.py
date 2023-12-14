def solution(p):
  return recover(p)

def recover(p):
  if not len(p):
    return ''

  u, v = split(p)
  if check(u):
    return u + recover(v)

  res = '(' + recover(v) + ')'
  return res + flip(u[1:-1])

def flip(u):
  res = ''
  for s in u:
    if s == '(': res += ')'
    else: res += '('
  return res

def check(u):
  cnt = 0
  for s in u:
    if s == '(':
      cnt += 1

    elif cnt == 0:
      return False

    else:
      cnt -= 1

  return cnt == 0

def split(p):
  left, right = 0, 0
  for i in range(len(p)):
    if p[i] == '(':
      left += 1
    else:
      right += 1

    if left == right:
      return p[:i+1], p[i+1:]

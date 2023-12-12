from itertools import permutations

def solution(expression):
  exp, num = [], ''
  oprs = ['*', '-', '+']

  for e in expression:
    if e not in oprs:
      num += e

    else:
      exp.append(int(num))
      exp.append(e)
      num = ''

  exp.append(int(num))

  answer = 0
  for perm in permutations(oprs, 3):
    res = exp[:]
    for opr in perm:
      res = solve(res, opr)

    answer = max(answer, abs(res[0]))

  return answer

def solve(exp, opr):
  res, idx = [], 0
  while idx < len(exp):
    if exp[idx] != opr:
      res.append(exp[idx])
      idx += 1

    else:
      v1 = res.pop()
      v2 = exp[idx+1]
      res.append(cal(v1, v2, exp[idx]))
      idx += 2

  return res

def cal(v1, v2, opr):
  if opr == '*':
    return v1 * v2

  if opr == '+':
    return v1 + v2

  if opr == '-':
    return v1 - v2
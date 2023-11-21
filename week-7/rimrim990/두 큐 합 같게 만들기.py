from collections import deque

def solution(q1, q2):
  q1, q2 = deque(q1), deque(q2)
  s1, s2 = sum(q1), sum(q2)

  cnt = 0
  for _ in range(4 * len(q1)):
    if s1 == s2:
      return cnt

    cnt += 1
    if s1 > s2:
      val = q1.popleft(); s1 -= val
      q2.append(val); s2 += val

    else:
      val = q2.popleft(); s2 -= val
      q1.append(val); s1 += val

  return -1
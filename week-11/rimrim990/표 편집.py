from heapq import heappop, heappush, heapify

def solution(n, k, cmd):
  top = [-i for i in range(k)]; heapify(top)
  bottom = [i for i in range(k, n)]; heapify(bottom)
  undo = []

  for c in cmd:
    if c[0] == 'U':
      x = int(c.split()[1])
      while x:
        if not top:
          while len(bottom) > 1:
            v = heappop(bottom)
            heappush(top, -v)

        v = heappop(top)
        heappush(bottom, -v)
        x -= 1

    elif c[0] == 'D':
      x = int(c.split()[1])
      while x:
        v = heappop(bottom)
        heappush(top, -v)
        x -= 1

        if not bottom:
          while top:
            v = heappop(top)
            heappush(bottom, -v)

    elif c[0] == 'C':
      undo.append(heappop(bottom))
      if not bottom:
        v = heappop(top)
        heappush(bottom, -v)

    else:
      z = undo.pop()
      if z < bottom[0]:
        heappush(top, -z)

      else:
        heappush(bottom, z)

  answer = ['X' for _ in range(n)]
  for t in top:
    answer[-t] = 'O'
  for b in bottom:
    answer[b] = 'O'
  return ''.join(answer)

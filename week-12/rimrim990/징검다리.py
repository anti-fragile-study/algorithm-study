def solution(distance, rocks, n):
  rocks.append(0)
  rocks.append(distance)
  rocks.sort()

  ans = 0
  start, end = 1, distance
  while start <= end:
    # 바위 간 거리의 최솟값
    min_dist = (start + end) // 2

    # 최소 거리 충족 가능
    if possible(rocks, n, min_dist):
      ans = max(ans, min_dist)
      start = min_dist + 1

    # 최소 거리 충족 불가능
    else:
      end = min_dist - 1

  return ans

def possible(rocks, n, min_dist):
  idx = 0
  while idx < len(rocks)-1:
    nxt = idx + 1
    while nxt < len(rocks) and rocks[nxt] - rocks[idx] < min_dist:
      # 더 이상 바위를 부술 수 없음
      if n == 0:
        return False

      n -= 1
      nxt += 1

    idx = nxt

  return True

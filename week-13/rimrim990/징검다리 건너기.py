def solution(stones, k):
  ans = 0
  start, end = 1, max(stones)
  while start <= end:
    mid = (start + end) // 2
    if check(stones, k, mid):
      ans = max(ans, mid)
      start = mid + 1

    else:
      end = mid - 1

  return ans

def check(stones, k, cnt):
  # 마지막 친구가 징검다리를 건너갈 수 있는지 확인
  remain = k
  for stone in stones:
    if stone <= cnt-1:
      remain -= 1
      if remain == 0: return False

    else:
      remain = k

  return True

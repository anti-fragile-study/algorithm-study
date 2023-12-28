from collections import defaultdict

def solution(n, lost, reserve):
  # 여벌 체육복의 수
  cnt = defaultdict(int)
  for r in reserve:
    cnt[r] += 1
  for l in lost:
    cnt[l] -= 1

  # 체육복 대여
  ans = 0
  for i in range(1, n+1):
    if cnt[i] > -1:
      ans += 1
      continue

    if cnt[i-1] > 0:
      cnt[i-1] -= 1
      cnt[i] += 1
      ans += 1
      continue

    if cnt[i+1] > 0:
      cnt[i+1] -= 1
      cnt[i] += 1
      ans += 1

  return ans

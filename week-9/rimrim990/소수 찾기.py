from itertools import permutations

MAX = 10_000_000
counts = [False for _ in range(MAX)]

def solution(nums):
  n = len(nums)
  nums = list(nums)

  # 만들 수 있는 모든 경우의 수 탐색
  for i in range(n):
    for cands in permutations(nums, i+1):
      num = int(''.join(cands))
      if is_prime(num):
        counts[num] = True

  return counts.count(True)

def is_prime(num):
  if num < 2:
    return False

  limit = int(num ** 0.5)
  for i in range(2, limit+1):
    if num % i == 0:
      return False

  return True
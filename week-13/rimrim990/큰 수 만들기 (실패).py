def solution(number, k):
  # 각 숫자들의 위치
  idxes = [-1 for _ in range(10)]
  nums = [(num, idx) for idx, num in enumerate(number)]
  nums.sort()

  ans = []
  while len(ans) < len(number) - k:
    num, idx = nums.pop()
    # 뒤에 나보다 큰 수가 있으면 안 된다. (결과물이 작아짐)
    if check(num, idx, idxes):
      idxes[int(num)] = max(idxes[int(num)], idx)
      ans.append(num)

  return ''.join(ans)


def check(num, idx, idxes):
  for i in idxes[int(num):]:
    if idx < i:
      return False
  return True

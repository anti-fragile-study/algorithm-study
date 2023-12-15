import sys
sys.setrecursionlimit(10_000)

roots = {}

def solution(k, room_number):
  answer = []
  for num in room_number:
    # 방이 비어 있음.
    if not roots.get(num):
      answer.append(num)
      roots[num] = num
      merge(num)
      continue

    # 방이 선점되어 있음.
    last_num = find(num)
    roots[last_num+1] = last_num+1
    answer.append(last_num+1)
    merge(last_num+1)

  return answer

def merge(a):
  # 왼쪽 트리와 합친다.
  if roots.get(a-1):
    union(a, a-1)

  # 오른쪽 트리와 합친다.
  if roots.get(a+1):
    union(a, a+1)

def union(a, b):
  pa = find(a)
  pb = find(b)
  if pa > pb:
    roots[pb] = pa
  else:
    roots[pa] = pb

def find(a):
  if roots.get(a) != a:
    roots[a] = find(roots[a])
  return roots[a]

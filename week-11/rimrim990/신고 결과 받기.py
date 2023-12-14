from collections import defaultdict

def solution(id_list, report, k):
  # 신고 기록
  cnt = defaultdict(set)
  for r in report:
    a, b = r.split()
    cnt[b].add(a)

  # 메일 개수
  ans = defaultdict(int)
  for key in cnt.keys():
    if len(cnt[key]) < k:
      continue

    for val in cnt[key]:
      ans[val] += 1

  res = [ans[id_] for id_ in id_list]
  return res

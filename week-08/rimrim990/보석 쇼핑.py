def solution(gems):
  cnt = {}
  for gem in gems:
    cnt[gem] = 0

  bag = {gems[0]}
  cnt[gems[0]] += 1
  answer = [1, len(gems)]

  end = 1
  for start in range(len(gems)):
    while len(bag) < len(cnt) and end < len(gems):
      bag.add(gems[end])
      cnt[gems[end]] += 1
      end += 1

    if len(bag) == len(cnt) and end-1-start < answer[1]-answer[0]:
      answer = [start+1, end]

    cnt[gems[start]] -= 1
    if cnt[gems[start]] == 0:
      bag.remove(gems[start])

  return answer
from bisect import bisect_left
from collections import defaultdict

def solution(infos, query):
  dict = defaultdict(list)
  # 50_000
  for info in infos:
    args = info.split()
    keys = generate_keys(args[:4])
    for key in keys:
      score = int(args[4])
      dict[key].append(score)

  # 108 * 50_000 * log(50_000)
  for key in dict:
    dict[key].sort()

  # 100_000
  answer = [0 for _ in range(len(query))]
  for i in range(len(query)):
    args = query[i].replace(" and ", ",")
    key, score = args.split()
    score = int(score)

    # log(50_000)
    idx = bisect_left(dict[key], score)
    answer[i] = len(dict[key]) - idx

  return answer

def generate_keys(args):
  keys = ["-", args[0]]
  for i in range(1, 4):
    generated = []

    for j in range(len(keys)):
      generated.append(keys[j] + "," + args[i])
      generated.append(keys[j] + "," + "-")
    keys = generated

  return keys
from itertools import combinations
from collections import defaultdict

def solution(orders, course):
  orders = [sorted(order) for order in orders]
  answer = []

  for count in course:
    cands = []
    for order in orders:
      cands += [''.join(cand) for cand in combinations(order, count)]
    freq, values = get_most_commons(cands)
    if freq > 1: answer += values

  return sorted(answer)

def get_most_commons(values):
  value_count = defaultdict(int)
  for value in values:
    value_count[value] += 1

  max_freq = 0; values = []
  for value in value_count:
    if value_count[value] > max_freq:
      max_freq = value_count[value]
      values = [value]
    elif value_count[value] == max_freq:
      values.append(value)

  return max_freq, values
from itertools import combinations

def solution(in_orders, course):
  orders = []; order_bits = []
  for order in in_orders:
    orders.append(sorted(order))
    order_bits.append(get_bit(order))

  answer = []
  for count in course:
    cands = set()
    for i in range(len(orders)):
      for comb in combinations(orders[i], count):
        cands.add(''.join(comb))

    max_score = 0; values = []
    for cand in cands:

      score = cal_score(order_bits, cand)
      if score > max_score:
        max_score = score
        values = [cand]

      elif score == max_score:
        values.append(cand)

    if max_score > 1:
      answer += values

  return sorted(answer)

def cal_score(order_bits, order):
  bit = get_bit(order)
  score = 0
  for order_bit in order_bits:
    if bit & order_bit == bit:
      score += 1
  return score

def get_bit(alphas):
  bits = 0
  for alpha in alphas:
    idx = ord(alpha) - ord('A')
    bits |= 1 << idx
  return bits
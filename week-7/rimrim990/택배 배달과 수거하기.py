def solution(cap, n, deliveries, pickups):
  # 배달해야 할 집
  deliver = -1
  for i in range(n-1, -1, -1):
    if deliveries[i] > 0:
      deliver = i
      break

  # 수거해야 할 집
  pick = -1
  for i in range(n-1, -1, -1):
    if pickups[i] > 0:
      pick = i
      break

  # 투 포인터
  answer = 0
  while deliver >= 0 or pick >= 0:
    answer += 2 * (max(pick, deliver) + 1)

    # 배달하기
    hand = 0
    while deliver >= 0 and hand <= cap:
      if hand + deliveries[deliver] <= cap:
        hand += deliveries[deliver]
        deliver -= 1
        continue

      available = cap - hand
      deliveries[deliver] -= available
      break

    # 수거하기
    hand = 0
    while pick >= 0 and hand <= cap:
      if hand + pickups[pick] <= cap:
        hand += pickups[pick]
        pick -= 1
        continue

      available = cap - hand
      pickups[pick] -= available
      break

  return answer
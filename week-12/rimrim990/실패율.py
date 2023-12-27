def solution(N, stages):
  clear = [0] * (N+3)
  reached = [0] * (N+3)

  for stage in stages:
    # 스테이지를 클리어한 사용자의 수
    clear[1] += 1
    clear[stage] -= 1

    # 스테이지에 도달한 사용자의 수
    reached[1] += 1
    reached[stage+1] -= 1

  # 누적합
  fails = []
  for i in range(1, N+1):
    clear[i] += clear[i-1]
    reached[i] += reached[i-1]

    if reached[i]:
      val = (reached[i]-clear[i]) / reached[i]
    else:
      val = 0

    fails.append((val, i))

  fails.sort(reverse=True, key=lambda x: (x[0], -x[1]))
  return list(map(lambda x: x[1], fails))

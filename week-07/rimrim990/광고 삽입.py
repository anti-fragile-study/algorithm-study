def solution(play_time, adv_time, logs):
  play_time = to_ss(play_time)
  adv_time = to_ss(adv_time)

  cnt = [0 for _ in range(play_time+1)]
  for log in logs:
    start, end = log.split("-")
    start, end = to_ss(start), to_ss(end)
    cnt[start] += 1
    cnt[end] -=1

  # 시청자 수
  for i in range(1, play_time+1):
    cnt[i] += cnt[i-1]

  # 시청자 수의 누적 합
  for i in range(1, play_time+1):
    cnt[i] += cnt[i-1]

  # 부분합이 최대가 되는 구간 탐색
  max_val, start = cnt[adv_time-1], 0
  for i in range(1, play_time-adv_time+1):
    val = cnt[i+adv_time-1] - cnt[i-1]
    if val > max_val:
      max_val = val
      start = i

  return to_str(start)

def to_str(time):
  ss = str(time % 60); time //= 60
  mm = str(time % 60); time //= 60
  hh = str(time)

  ss = ('0' + ss)[-2:]
  mm = ('0' + mm)[-2:]
  hh = ('0' + hh)[-2:]

  return ":".join([hh, mm, ss])

def to_ss(time):
  hh, mm, ss = time.split(":")
  return int(hh)*60*60 + int(mm)*60 + int(ss)
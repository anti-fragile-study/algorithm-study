from heapq import heappush, heappop

def solution(jobs):
  n = len(jobs)
  jobs.sort(reverse=True)

  hq = []
  acc, cur = 0, 0

  while jobs or hq:
    # 현재 시간까지 들어온 요청들
    while jobs and jobs[-1][0] <= cur:
      req = jobs.pop()
      heappush(hq, (req[1], req[0]))

    # 처리 시간이 가장 짧은 요청 처리
    if hq:
      duration, at = heappop(hq)
      cur += duration
      acc += (cur - at)

    # 요청이 들어오는 시간으로 이동
    else:
      cur = jobs[-1][0]

  return acc // n

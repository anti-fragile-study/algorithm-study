def solution(uid, bid):
  answer = set()
  answer |= search(bid, uid, 0, 0)
  return len(answer)

def match(bid, uid):
  if len(bid) != len(uid):
    return False

  for i in range(len(bid)):
    if bid[i] not in [uid[i], '*']:
      return False

  return True

def search(bid, uid, bidx, used):
  if bidx == len(bid):
    return {used}

  cnt = set()
  for i in range(len(uid)):
    if match(bid[bidx], uid[i]):
      bit = 1 << i
      if bit & used == 0:
        cnt |= search(bid, uid, bidx+1, used | bit)

  return cnt
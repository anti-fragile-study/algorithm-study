from itertools import permutations
from collections import defaultdict, deque

N = 4
MAX = int(1e5)

dy = [0,0,1,-1]
dx = [1,-1,0,0]

def solution(board, r, c):
  # 모든 카드 패의 위치
  cards = defaultdict(list)
  for y in range(N):
    for x in range(N):
      if board[y][x] != 0:
        num = board[y][x]
        cards[num].append((y, x))

  ans = MAX

  # 카드를 뒤집을 순서 선택
  for cands in permutations(cards.keys(), len(cards)):
    copy = [elem[:] for elem in board]
    ans = min(ans, game(copy, (r, c), cands, cards))

  return ans

def game(board, cur, orders, cards):
  total_dist = 0
  for order in orders:
    pos = cards[order]
    dist1 = move(board, cur, pos[0]) + move(board, pos[0], pos[1])
    dist2 = move(board, cur, pos[1]) + move(board, pos[1], pos[0])

    board[pos[0][0]][pos[0][1]] = 0
    board[pos[1][0]][pos[1][1]] = 0

    if dist1 <= dist2:
      total_dist += dist1 + 2
      cur = pos[1]

    else:
      total_dist += dist2 + 2
      cur = pos[0]

  return total_dist

def move(board, start, end):
  visited = [[False] * N for _ in range(N)]
  visited[start[0]][start[1]] = True
  dq = deque([(start, 0)])

  while dq:
    cur, cnt = dq.popleft()
    if cur == end:
      return cnt

    # 방향키 이동
    for i in range(4):
      ny = cur[0] + dy[i]
      nx = cur[1] + dx[i]
      if check(ny, nx) and not visited[ny][nx]:
        visited[ny][nx] = True
        dq.append([(ny, nx), cnt+1])

    # ctrl 이동
    for i in range(4):
      ny = cur[0]
      nx = cur[1]
      while check(ny+dy[i], nx+dx[i]):
        ny += dy[i]
        nx += dx[i]

        if board[ny][nx] != 0:
          break

      if (ny != cur[0] or nx != cur[1]) and not visited[ny][nx]:
        visited[ny][nx] = True
        dq.append([(ny, nx), cnt+1])

def check(y, x):
  return 0 <= y < N and 0 <= x < N

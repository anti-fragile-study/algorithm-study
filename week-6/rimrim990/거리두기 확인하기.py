def solution(places):
  n = 5
  answer = []
  for i in range(n):
    res = check_distance(places[i], n)
    answer.append(res)

  return answer

def check_distance(place, n):
  for y in range(n):
    for x in range(n):
      if place[y][x] != 'P':
        continue

      if not (check_line(place, y, x, n) and check_diag(place, y, x, n)):
        return 0

  return 1

# 직선 거리 체크
def check_line(place, y, x, n):
  dy = [-1, 1, 0, 0]
  dx = [0, 0, -1, 1]

  # 맨해튼 거리 1
  for i in range(4):
    ny, nx = y + dy[i], x + dx[i]
    if 0 <= ny < n and 0 <= nx < n:
      if place[ny][nx] == 'P':
        return False

  # 맨해튼 거리 2
  for i in range(4):
    ny = y + dy[i] * 2
    nx = x + dx[i] * 2
    if 0 <= ny < n and 0 <= nx < n:
      if place[ny][nx] != 'P':
        continue

      my, mx = (ny + y) // 2, (nx + x) // 2
      if place[my][mx] != 'X':
        return False

  return True

# 대각선 거리 체크
def check_diag(place, y, x, n):
  dy = [1, 1, -1, -1]
  dx = [-1, 1, -1, 1]
  for i in range(4):
    ny, nx = y + dy[i], x + dx[i]
    if 0 <= ny < n and 0 <= nx < n:
      if place[ny][nx] != 'P':
        continue

      if not (place[ny][x] == 'X' and place[y][nx] == 'X'):
        return False

  return True
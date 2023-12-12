def solution(numbers, hand):
  answer = ''
  left, right = (3, 0), (3, 2)
  for num in numbers:
    y, x = get_pos(num)

    if x == 0:
      left = (y, x)
      answer += 'L'
      continue

    if x == 2:
      right = (y, x)
      answer += 'R'
      continue

    ldist = abs(left[0]-y) + abs(left[1]-x)
    rdist = abs(right[0]-y) + abs(right[1]-x)
    if (ldist < rdist) or (ldist == rdist and hand == "left"):
      answer += 'L'
      left = (y, x)

    else:
      answer += 'R'
      right = (y, x)

  return answer

def get_pos(num):
  if num == 0:
    return 3, 1

  y = (num-1) // 3
  x = (num-1) % 3
  return y, x
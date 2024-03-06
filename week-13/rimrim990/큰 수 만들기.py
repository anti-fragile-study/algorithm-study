def solution(number, k):
  t = len(number)-k
  answer = []

  for i in range(len(number)):
    while answer and answer[-1] < number[i] and len(number)-i+len(answer)-1 >= t:
      answer.pop()

    if len(answer) < t:
      answer.append(number[i])

  return ''.join(answer)

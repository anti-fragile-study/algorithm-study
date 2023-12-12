N = 5
alphas = ['A', 'E', 'I', 'O', 'U']

def solution(word):
  answer = 0

  # ex. EIO
  for pos in range(len(word)):
    # if pos = 2, EIAA ~ EIIUU
    idx = alphas.index(word[pos])
    for length in range(1, N-pos):
      answer += idx * (N ** length)

    # if pos = 2, EIA ~ EIO
    answer += idx + 1

  return answer

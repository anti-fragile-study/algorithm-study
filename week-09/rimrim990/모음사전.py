from bisect import bisect_right

N = 5
words = []

def solution(word):
  alphas = ['A', 'E', 'I', 'O', 'U']
  make_words('', 0, alphas)

  return bisect_right(words, word)

def make_words(word, idx, alphas):
  if len(word) == N:
    return

  for i in range(idx, len(alphas)):
    w = word + alphas[i]
    words.append(w)
    make_words(w, idx, alphas)

def solution(n, words):
    visited = {}
    fail = False
    pre_word = words[0]
    visited[pre_word] = True
    for i in range(1, len(words)):
        word = words[i]
        if word[0] != pre_word[-1]:
            fail = True
            break
        if word in visited:
            fail = True
            break
        visited[word] = True
        pre_word = word
    if fail:
        return [i % n + 1, i // n + 1]
    return [0, 0]
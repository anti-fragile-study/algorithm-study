from itertools import product

def solution(word):
    arr = []
    for n in range(1, 6):
        for x in product("AEIOU", repeat=n):
            arr.append("".join(x));
    answer = 0
    for _word in sorted(arr):
        if _word == word:
            return answer + 1
        answer += 1
    return answer
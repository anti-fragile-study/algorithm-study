from itertools import product


def solution(word):
    consonants = "AEIOU"

    dictionary = []
    for length in range(len(consonants) + 1):
        for case in product(consonants, repeat=length):
            dictionary.append("".join(case))

    dictionary.sort()

    return dictionary.index(word)

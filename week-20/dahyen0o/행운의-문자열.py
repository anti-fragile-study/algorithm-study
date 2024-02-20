from itertools import permutations

S = list(input())

lucky = set()

for string in permutations(S, len(S)):
    for i in range(len(string) - 1):
        if string[i] == string[i + 1]:
            break
    else:
        lucky.add(''.join(string))

print(len(lucky))

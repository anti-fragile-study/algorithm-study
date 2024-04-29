import sys

"""
1
12
BAP
BAPC.in
BAPC.out
BAPC.tex
filter
filename
filenames
clean
cleanup.IN1
cleanup.IN2
cleanup.out
problem.tex
5
BAPC
files
cleanup
cleanup.IN
cleaning
-> 9
"""

"""
1
2
A
B
0
-> 1
"""

"""
1
4
BAP
BAPC.in
BAPC.out
BAPC.tex
1
BAPC
-> 2
"""

"""
1
3
abc
a
c
1
b
-> 2
"""

input = lambda: sys.stdin.readline().strip()

n = int(input())
cases = []
for _ in range(n):
    t = int(input())
    targets = [input() for _ in range(t)]
    s = int(input())
    skips = [input() for _ in range(s)]
    cases.append((targets, skips))


def solution(cases):
    answer = []
    for targets, skips in cases:
        trie = {}
        for skip in skips:
            add(trie, skip)
        if not trie:
            answer.append(1)
            continue

        commands = set()
        for target in targets:
            idx = examine(trie, target, 0)
            if idx == -1:  # no wildcard
                commands.add(target)
            else:
                commands.add(target[:idx + 1] + "*")

        answer.append(len(commands))

    return answer

def add(trie, string):
    if not string:
        return

    if string[0] not in trie:
        trie[string[0]] = {}

    add(trie[string[0]], string[1:])


def examine(trie, string, idx):
    if idx >= len(string):
        return -1

    if string[idx] in trie:
        return examine(trie[string[idx]], string, idx + 1)
    else:
        return idx

print("\n".join(map(str, solution(cases))))

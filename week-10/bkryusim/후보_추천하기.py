import sys
from collections import defaultdict


def solve():
    N = int(sys.stdin.readline().rstrip())
    K = int(sys.stdin.readline().rstrip())
    votes = list(map(int, sys.stdin.readline().split()))

    data = defaultdict(int)
    final = set()
    turn = defaultdict(int)

    for i, vote in enumerate(votes):
        if vote in final:
            data[vote] += 1

        if len(final) < N:
            if vote not in final:
                turn[vote] = i
            final.add(vote)
            continue

        if vote not in final:
            worst = min(map(lambda x: (data[x], turn[x], x), final))[2]
            data[worst] = 0
            final.remove(worst)

            turn[vote] = i
            final.add(vote)
            continue

    print(' '.join(map(str, sorted(final))))


solve()

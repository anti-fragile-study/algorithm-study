import sys


def solve():
    words = ["pi", "ka", "chu"]
    S = sys.stdin.readline().rstrip()

    while len(S) > 0:
        if S[:2] in words:
            S = S[2:]
            continue
        if S[:3] in words:
            S = S[3:]
            continue

        print("NO")
        return
    print("YES")


solve()

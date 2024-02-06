import sys

input = sys.stdin.readline
n = int(input())

def solution():
    number = list(map(int, input().split()))
    number.sort()

    return number[0], number[-1]

for _ in range(n):
    input()
    print(*solution())

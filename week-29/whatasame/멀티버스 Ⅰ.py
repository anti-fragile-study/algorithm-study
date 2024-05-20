import sys
from itertools import combinations

"""
3 3
1 1 1
1 1 1
1 1 1
-> 3

2 3
5 7 6
1 2 3
-> 0
"""

input = lambda:sys.stdin.readline().strip()

m, n = map(int, input().split())
planets = [list(map(int, input().split())) for _ in range(m)]

def solution(planets):
    # 랭킹 구하기 -> O(n^2)
    orders = []
    for planet in planets:
        sorted_planet = sorted(planet)
        orders.append([sorted_planet.index(e) for e in planet])

    # 순서들의 조합으로 쌍 구하기 -> O(m^2n)
    answer = 0
    for case in combinations(orders, 2):
        if case[0] == case[1]:
            answer += 1

    return answer

print(solution(planets))

from itertools import combinations

N, L, R, X = map(int, input().split())
problems = list(map(int, input().split()))

answer = 0

for count in range(2, N+1):
    for choice in combinations(problems, count):
        if (L <= sum(choice) <= R) and (X <= max(choice) - min(choice)):
            answer += 1    

print(answer)    
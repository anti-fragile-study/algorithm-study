from itertools import combinations

prime = [True] * (10000)
root = int(10000**0.5)
for x in range(2, root+1):
    if not prime[x]: continue
    for y in range(2*x, 10000, x):
        prime[y] = False
        
def solution(nums):
    answer = 0
    for ns in combinations(nums, 3):
        if prime[sum(ns)]:
            answer += 1
    return answer
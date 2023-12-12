from itertools import permutations
import math

def solution(numbers):
    n = 10000000
    prime = [True] * n
    root = math.ceil(n ** 0.5)
    prime[0], prime[1] = False, False
    
    for i in range(2, root):
        if prime[i]:
            for j in range(i * 2, n, i):
                prime[j] = False

    primes = set()
    for i in range(1, len(numbers) + 1):
        for x in permutations(numbers, i):
            num = int("".join(x))
            if prime[num]:
                primes.add(num)
    return len(primes)
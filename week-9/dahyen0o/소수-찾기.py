import math
from itertools import permutations

def solution(numbers):
    answer = set()
    for counts in range(1, len(numbers) + 1):
        for case in permutations(numbers, counts):
            num = int(''.join(case))
            if is_prime(num):
                answer.add(num)
    return len(answer)

def is_prime(num):
    if num == 0 or num == 1:
        return False
    
    for i in range(2, int(math.sqrt(num)) + 1):
        if num % i == 0:
            return False
    return True

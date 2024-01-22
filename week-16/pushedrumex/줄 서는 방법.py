from itertools import permutations
from math import factorial, ceil

def solution(n, k):
    answer = []
    nums = list(range(1, n+1))
    
    while n > 0:
        temp = factorial(n) // n
        num = nums[ceil(k / temp) - 1]
        answer.append(num)
        nums.remove(num)
        n -= 1
        k %= temp
    return answer
# int("013") => 13

from itertools import *


def solution(numbers):
    # 에라토스테네스의 체 선언
    max_num = 9_999_999
    primes = [True for _ in range(max_num + 1)]  # 0 ~ 9_999_999
    primes[0] = primes[1] = False
    for num in range(2, int(max_num ** 0.5) + 1):
        if primes[num]:
            for times in range(num + num, max_num + 1, num):
                primes[times] = False

    # 생성 가능한 모든 숫자에 대하여 소수 확인
    answer = set()
    for length in range(1, len(numbers) + 1):
        for case in permutations(numbers, length):
            num = int("".join(case))
            print(num)
            if primes[num]:
                answer.add(num)

    return len(answer)

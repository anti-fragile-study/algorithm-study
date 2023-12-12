import math


def solution(numbers):
    answer = set()

    def check(n):
        if n == 1 or n == 0:
            return False
        t = int(math.sqrt(n))
        for i in range(2, t + 1):
            if n % i == 0:
                return False
        return True

    def search(l, s):
        for i, n in enumerate(l):
            if int(s + n) not in answer and check(int(s + n)):
                answer.add(int(s + n))

            search(l[:i] + l[i + 1:], s + n)

    search(list(numbers), "")

    return len(answer)

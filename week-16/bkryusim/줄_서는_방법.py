def factorial(n):
    if n < 1:
        return 1
    else:
        return n * factorial(n - 1)


def solution(n, k):
    answer = []
    nums = [i for i in range(1, n + 1)]

    while n != 0:
        f = factorial(n - 1)
        i = k // f
        k = k % f

        if k == 0:
            answer.append(nums.pop(i - 1))
        else:
            answer.append(nums.pop(i))
        n -= 1

    return answer

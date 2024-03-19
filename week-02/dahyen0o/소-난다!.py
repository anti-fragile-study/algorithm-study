from itertools import combinations

def set_primes(num):
    is_prime = [True for _ in range(num + 1)]
    is_prime[0] = is_prime[1] = False

    for i in range(2, num + 1):
        if not is_prime[i]: continue
        for j in range(i * i, num + 1, i):
            is_prime[j] = False

    return is_prime

N, M = map(int, input().split(' '))
cows = list(map(int, input().split(' ')))

is_prime = set_primes(sum(cows))
# print(is_prime)

answer = set()

for partial_sum in combinations(cows, M):
    partial_sum = sum(partial_sum)
    if is_prime[partial_sum]:
        answer.add(partial_sum)

print(' '.join(map(str, sorted(answer))) if answer else -1)

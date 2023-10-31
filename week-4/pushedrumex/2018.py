N = int(input())

# N의 약수이면서 홀수인 것의 개수
answer = 0
for n in range(1, N+1):
    if N % n == 0 and n % 2 != 0:
        answer += 1

print(answer)
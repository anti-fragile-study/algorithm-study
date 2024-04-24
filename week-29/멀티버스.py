M, N = map(int, input().split())

universe = []
for _ in range(M):
    universe.append(list(map(int, input().split())))

def is_balance(A, B):
    for j in range(N-1):
        for k in range(j+1, N):
            if not ((A[j] < A[k] and B[j] < B[k]) or (A[j] == A[k] and B[j] == B[k]) or (A[j] > A[k] and B[j] > B[k])):
                return False
            
    return True

answer = 0
for i in range(M-1):
    A = universe[i]
    for j in range(i+1, M):
        B = universe[j]
        if is_balance(A, B):
            answer += 1


print(answer)
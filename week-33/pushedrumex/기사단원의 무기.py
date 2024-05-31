def count(n):
    result = 0
    root = int(n**0.5)
    for i in range(1, root+1):
        if n % i == 0:
            if i**2 == n:
                result += 1
            else:
                result += 2
    return result
        
def solution(number, limit, power):
    answer = 0
    for i in range(1, number+1):
        attack = count(i)
        if attack > limit:
            answer += power
        else:
            answer += attack
        
    return answer
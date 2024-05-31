def solution(num):
    if num == 1:
        return 0
    
    time = 0
    while time < 500:
        time += 1
        if num % 2 == 0:
            num //= 2
        else:
            num *= 3
            num += 1
            
        if num == 1:
            return time
        
    return -1
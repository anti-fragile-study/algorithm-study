# 가장 멀리 있는 집부터 해결한다
from collections import deque

def solution(cap, n, deliveries, pickups):
    answer = 0
    delivery_q = deque(deliveries)
    pickup_q = deque(pickups)
    
    while delivery_q or pickup_q:
        while delivery_q and delivery_q[-1] == 0:
            delivery_q.pop()
        while pickup_q and pickup_q[-1] == 0:
            pickup_q.pop()
            
        delivery_cost, pickup_cost = len(delivery_q), len(pickup_q)
        answer += max(delivery_cost, pickup_cost) * 2
        
        temp = cap
        while temp > 0 and delivery_q:
            택배 = delivery_q.pop()
            if 택배 > temp:
                delivery_q.append(택배 - temp)
                break
            else:
                temp -= 택배
        
        temp = cap
        while temp > 0 and pickup_q:
            빈택배 = pickup_q.pop()
            if 빈택배 > temp:
                pickup_q.append(빈택배 - temp)
                break
            else:
                temp -= 빈택배
                
    return answer
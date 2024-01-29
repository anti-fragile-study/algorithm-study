# k 가 너무 큼
# r: 회전 수
# len(food_times) > k : 한바퀴도 못 돔
# r = min(food_times)
# 만약 min(food_times) * len(food_times) > k 라면, r = k // len(food_times)
from heapq import heappush, heappop, heapify

def solution(food_times, k):
    food = {}
    for i, t in enumerate(food_times):
        food[i] = t
        
    q = list(food.values())
    heapify(q)
    
    count = len(food)
    total_r = 0
    while k >= count:
        r = q[0] - total_r
        if r * count > k:
            r = k // count
        
        k -= r * count
        total_r += r
        
        foods = list(food.keys())[:]
        for x in foods:
            if food[x] > r:
                food[x] -= r
            else:
                food.pop(x)
                heappop(q)
                count -= 1
                
        if count == 0: return -1
    
    return list(food.keys())[k] + 1
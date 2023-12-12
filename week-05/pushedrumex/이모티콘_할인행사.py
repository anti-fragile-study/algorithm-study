from itertools import product

def solution(users, emoticons):
    rates = [10, 20, 30, 40]
    N = len(emoticons)
    join, amount = 0, 0
    
    for rate in product(rates, repeat=N):
        _join, _amount = 0, 0
        for user in users:
            temp_amount = 0
            for i in range(N):
                if user[0] <= rate[i]:
                    temp_amount += emoticons[i] * (100 - rate[i]) * 0.01
            if temp_amount >= user[1]:
                _join += 1
            else:
                _amount += temp_amount
        
        if _join > join:
            join = _join
            amount = _amount
        elif join == _join:
            amount = max(amount, _amount)
        
    return [join, amount]
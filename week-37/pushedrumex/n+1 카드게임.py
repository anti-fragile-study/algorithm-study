from collections import deque

INF = int(1e9)
# 일단 카드를 모두 갖고, 카드를 사용해야할 때 coin 지불
def solution(coin, cards):
    global my_cards, cost, n
    
    n = len(cards)
    cards = deque(cards)
    my_cards = []
    cost = [INF] * (n+1)
    for _ in range(n//3):
        card = cards.popleft()
        my_cards.append(card)
        cost[card] = 0
        
    answer = 1
    while len(cards) > 0:
        # 카드 두 장 뽑기
        for _ in range(2):
            card = cards.popleft()
            my_cards.append(card)
            cost[card] = 1
            
        _cost, _cards = find()

        if _cost > coin:
            break
        
        coin -= _cost
        for card in _cards:
            my_cards.remove(card)

        answer += 1
    return answer

# 가장 저렴한 비용으로 n+1이 되는 카드 찾기
def find():
    _cost = INF
    _cards = []
    for card in my_cards:
        temp = cost[card] + cost[n+1-card]
        if _cost > temp:
            _cost = temp
            _cards = [card, n+1-card]
    
    return (_cost, _cards)

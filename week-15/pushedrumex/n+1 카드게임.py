# !! 카드 뽑는 순서가 정해져있음 !!
# 맨처음 카드 뭉치에 n/3 장의 카드를 가짐
# 각 라운드가 시작할 때 카드를 2장 뽑음
# 카드 한장 당 동전 하나를 소모해서 가지거나 동전을 소모하지 않고 버릴 수 있음
# 카드에 적힌 수의 합이 n+1 이 되도록 카드 두장을 내고 다음 라운드로 진행 가능
# 만약 카드 두장을 낼 수 없다면 게임 종료
# 카드 뭉치에 남은 카드가 없다면 게임은 종료

# 카드를 구매할지 말지 선택
# 하지만 카드가 쓰일 수 있을지 모름 -> 일단 가지고 있다가 사용할 때 coin--
# 최대한 coin 을 쓰지 않고 카드 내기
from collections import deque, defaultdict

new_card = defaultdict(bool)
mycards = deque()
def solution(coin, cards):
    cards = deque(cards)
    
    n = len(cards)
    for _ in range(n // 3):
        mycards.append(cards.popleft())
    
    answer = 1
    while cards:
        new_card1, new_card2 = cards.popleft(), cards.popleft()
        new_card[new_card1], new_card[new_card2] = True, True
        mycards.append(new_card1)
        mycards.append(new_card2)
        
        card_pair = get_card_pair(n+1)
        
        if card_pair == None: break
        
        card1, card2 = card_pair
        if new_card[card1]: coin -= 1
        if new_card[card2]: coin -= 1
        
        if coin < 0: break
        answer += 1
        
    return answer

def get_card_pair(k):
    # 새로운 카드 사용 안하고 탐색
    for i in range(len(mycards)):
        if new_card[mycards[i]]: break
        for j in range(len(mycards)):
            if i == j: continue
            if new_card[mycards[j]]: break
            card1, card2 = mycards[i], mycards[j]
            if card1 + card2 == k:
                mycards.remove(card1)
                mycards.remove(card2)
                
                return (card1, card2)

    for i in range(len(mycards)):
        for j in range(len(mycards)):
            if i == j: continue
            card1, card2 = mycards[i], mycards[j]
            if card1 + card2 == k:
                mycards.remove(card1)
                mycards.remove(card2)
                
                return (card1, card2)
    return None
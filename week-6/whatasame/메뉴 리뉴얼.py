# 한 번만 주문된 조합은 포함 X (e.g. 6번 손님의 E, H)
# 사전 순으로 오름차순 반환

from itertools import combinations
from collections import defaultdict

def solution(orders, courses):
    freq_map = defaultdict(int)
    length_map = defaultdict(list)
    
    # 각 주문에 대하여
    for order in orders:
        # 주문 문자열을 사전순 정렬
        order = sorted(order)
        # 주문에서 생성할 수 있는 combination
        for count in range(2, len(order) + 1):
            # map: {조합: 주문 수} 추가
            for menu in combinations(order, count):
                menu = "".join(menu)
                freq_map[menu] += 1
                length_map[len(menu)].append(menu)
        
    answer = []
    # 각 코스 개수에 대하여
    for course in courses:
        # 해당 개수의 최대 주문 수 구하기
        if not length_map[course]:
            continue
        most_menu = max(length_map[course], key = lambda menu : freq_map[menu])
        most_menu_count = freq_map[most_menu]
        
        # 최대 주문수와 동일한 메뉴 조합을 리스트에 추가
        for menu, count in freq_map.items():
            if count == most_menu_count and len(menu) == len(most_menu) and count >= 2:
                answer.append(menu)
                
    return sorted(answer)

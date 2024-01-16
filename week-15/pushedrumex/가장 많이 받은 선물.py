# 선물 지수 = give - take
# 선물 적게 받은 사람이 상대방 한테 선물
# 개수가 동일하다면 선물 지수가 낮은 사람이 선물
# 선물 지수도 동일하다면 주고받지 않음
def solution(friends, gifts):
    N = len(friends)
    record = [[0] * N for _ in range(N)]
    name_dic = {}
    for i in range(N):
        name_dic[friends[i]] = i
    
    # 선물 주고받은것 기록
    for gift in gifts:
        give, take = gift.split()
        record[name_dic[give]][name_dic[take]] += 1
    
    # 선물 지수 계산
    level = [0] * N
    for give in range(N):
        count_take = 0
        for take in range(N):
            count_take += record[take][give]
        level[give] += sum(record[give]) - count_take

    answer = 0
    for give in range(N):
        count_gift = 0
        for take in range(N):
            if give == take: continue
            
            if record[give][take] > record[take][give]:
                count_gift += 1
            elif record[give][take] == record[take][give] and level[give] > level[take]:
                count_gift += 1
                
        answer = max(answer, count_gift)
    return answer
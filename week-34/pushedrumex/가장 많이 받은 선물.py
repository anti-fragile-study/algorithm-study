def solution(friends, gifts):
    N = len(friends)
    friend_dic = {}
    for i in range(N):
        friend_dic[friends[i]] = i
    
    give_get = [[0] * N for _ in range(N)]
    for gift in gifts:
        give, get = gift.split()
        give_get[friend_dic[give]][friend_dic[get]] += 1
    
    get = [0] * N
    for A in range(N):
        for B in range(N):
            if give_get[A][B] > give_get[B][A]:
                get[A] += 1
            elif give_get[A][B] == give_get[B][A]:
                A_give = sum(give_get[A])
                B_give = sum(give_get[B])
                A_get, B_get = 0, 0
                for i in range(N):
                    A_get += give_get[i][A]
                    B_get += give_get[i][B]
                if A_give - A_get > B_give - B_get:
                    get[A] += 1
    
    return max(get)
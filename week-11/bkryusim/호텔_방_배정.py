def solution(k, room_number):
    answer = []
    sold = dict()

    for number in room_number:
        x = number
        update = [x]

        # 유니온 파인드
        while x in sold.keys() and x != sold[x]:
            x = sold[x]
            update.append(x)

        # 트리를 타고 올라가면서 탐색한 친구들 부모도 같이 수정해주기
        # 이거 안하면 효율성 걸림
        for m in update:
            sold[m] = x + 1

        answer.append(x)

    return answer

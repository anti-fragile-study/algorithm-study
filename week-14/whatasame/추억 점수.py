# 추억 점수: 사진 속의 인물들의 그리움의 합

def solution(names, yearnings, photos):
    # {사람 이름: 그리움}
    dictionary = {name: yearning for name, yearning in zip(names, yearnings)}

    # 모든 사진에 대하여
    # 맵에서 찾아서 합 구하기

    # 반환
    return [sum(map(lambda x: dictionary[x] if x in dictionary else 0, photo)) for photo in photos]

# 잘못된 아이디를 옳은 아이디로 추천
# 알파벳 소문자, 숫자, 특수문자[-_.]
# .는 처음과 끝에만 가능
# .는 연속으로 사용할 수 없음

import re

def solution(new_id):
    # 1단계
    new_id = new_id.lower()

    # 2단계
    new_id = re.sub('[^a-z\d\-\_\.]', '', new_id)

    # 3단계
    new_id = re.sub('\.+', '.', new_id)

    # 4단계
    new_id = re.sub('^\.|\.$', '', new_id)

    # 5단계
    if not new_id:
        new_id = "a"

    # 6단계
    new_id = new_id[:15]
    if new_id[-1] == ".":
        new_id = new_id[:-1]

    # 7단계
    while len(new_id) <= 2:
        new_id += new_id[-1]

    return new_id
        

# 1, 4, 7 -> 왼쪽
# 2, 5, 8, 0, -> 오른쪽
# 3, 6, 9 -> 둘 중 가까운 것 -> 거리 같다면 주 손 사용

# number 내 중복 번호 존재

keypad = {1: (0, 0), 2: (0, 1), 3: (0, 2),
          4: (1, 0), 5: (1, 1), 6: (1, 2),
          7: (2, 0), 8: (2, 1), 9: (2, 2),
          "*": (3, 0), 0: (3, 1), "#": (3, 2)}


def solution(numbers, hand):
    # 손가락 초기화
    left, right = keypad["*"], keypad["#"]

    answer = []
    for number in numbers:
        left, right, used = move(number, left, right, hand)
        answer.append(used)

    return "".join(map(str, answer))


def move(number, left, right, hand):
    if number in (1, 4, 7):
        return keypad[number], right, "L"
    if number in (3, 6, 9):
        return left, keypad[number], "R"

    left_distance = abs(keypad[number][0] - left[0]) + abs(keypad[number][1] - left[1])
    right_distance = abs(keypad[number][0] - right[0]) + abs(keypad[number][1] - right[1])

    if left_distance < right_distance:
        return keypad[number], right, "L"
    if left_distance > right_distance:
        return left, keypad[number], "R"
    else:
        if hand == "left":
            return keypad[number], right, "L"
        elif hand == "right":
            return left, keypad[number], "R"

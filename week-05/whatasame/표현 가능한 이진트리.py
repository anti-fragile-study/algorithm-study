# 수가 주어졌을 때 이진트리로 표현할 수 있는지 반환

# 노드의 개수는 1 -> 3 -> 7 -> 15 -> ... ->  2^n - 1 (n은 depth)
# 왼쪽에 0을 아무리 붙여도 십진수의 값은 변하지 않는다.

def solution(numbers):
    answer = []
    for number in numbers:
        # 주어진 숫자를 이진수로 변환 e.g. 58 -> 111010
        binary = to_binary(number)

        # 길이가 2^n-1꼴이 아닌 경우 필요한 만큼 0을 붙인다. 111010 -> 0111010
        padded = padding(binary)

        # 재귀적으로 서브 트리들의 root가 1인 지 판단한다.
        answer.append(1 if judge(padded, None) else 0)
        print("---")

    return answer

def to_binary(number):
    ret = []
    while number != 0:
        ret.append(number % 2)
        number //= 2

    return "".join(map(str, ret[::-1]))

def padding(binary):
    depth = 1

    while True:
        length = 2 ** depth - 1
        if length >= len(binary):
            return "0" * (length - len(binary)) + binary

        depth += 1

def judge(tree, parent):
    # 리프 노드의 좌우는 항상 True
    if parent and not tree:
        return True

    middle = len(tree) // 2
    left, right = tree[:middle], tree[middle + 1:]

    # 중앙이 1인 경우
    if tree[middle] == "1":
        if parent == "0":
            return False

    return judge(left, tree[middle]) and judge(right, tree[middle])

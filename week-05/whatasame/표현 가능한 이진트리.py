# 1~10,000를 이진수로 표현하여 이진트리를 만들 수 있는가?
# 숫자 = 1,000,000,000,000,000개

# 7:   111
# 42:  0101010 = 2 + 8 + 32 = 42
# 63:  0111111 = 1 + 2 + 4 + 8 + 16 + 32
# 111: 1101111 = 1 + 2 + 4 + 8 + 32 + 64 = 111

# 포화 이진 트리의 개수: 1, 3, 7, 15, ..., 2^k - 1
# 2^15 = 32768 -> 1~32768

# 1: 1               (O)
# 2: 10   -> 010     (O)
# 3: 11   -> 011     (O)
# 4: 100  -> 0000100 (X)
# 5: 101  -> 0000101 (X)
# ...
# 8: 1000 -> 0001000 (O)
# 9: 1001 -> 0001001 (X)

from itertools import *

def solution(numbers):
    forest = [[Node(None, "1", None)], [], [], []]
    for depth in range(3):
        for root in forest[depth]:
            for case in product(["0", "1"], repeat = 2 ** (depth + 1)):
                forest[depth + 1].append(inorder_case(copy_tree(root), list(case)))

    table = [False for _ in range(100_000)]
    for trees in forest:
        for tree in trees:
            table[int(str(tree), 2)] = True

    return [1 if table[number] else 0 for number in numbers]


class Node:
    def __init__(self, left, value, right):
        self.left = left
        self.value = value
        self.right = right

    def __str__(self):
        return "".join(inorder(self))

def inorder(node):
    values = []

    if node:
        values.extend(inorder(node.left))
        values.append(node.value)
        values.extend(inorder(node.right))

    return values

def inorder_case(node, case):
    if not node.left and not node.right:
        if node.value == "0":
            case.pop()
            case.pop()
            node.left = Node(None, "0", None)
            node.right = Node(None, "0", None)
        else:
            node.left = Node(None, case.pop(), None)
            node.right = Node(None, case.pop(), None)
    else:
        inorder_case(node.left, case)
        inorder_case(node.right, case)

    return node

def copy_tree(node):
    if node is None:
        return None
    else:
        return Node(
                copy_tree(node.left),
                node.value,
                copy_tree(node.right)
        )

from math import ceil, log2

# 포화 노드의 개수 -> 등비수열의 합 = 2^h - 1 (h은 트리의 높이)
def solution(numbers):
    answer = []
    for number in numbers:
        binary = list(map(int, list(bin(number)[2:])))
        node_count = 2 ** (ceil(log2(len(binary) + 1))) - 1
        tree = [0] * (node_count - len(binary)) + binary
        if (dfs(len(tree) // 2, tree)): answer.append(1)
        else: answer.append(0)

    return answer

def dfs(now, tree):
    if len(tree) == 1: return True
    left, right = now // 2, (len(tree) + now) // 2
    if tree[now] == 0 and tree[left] + tree[right] > 0:
        return False
    
    return dfs(left, tree[:now]) and dfs(right-now-1, tree[now+1:])
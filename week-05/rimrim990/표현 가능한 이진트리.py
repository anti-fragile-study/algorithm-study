def solution(numbers):
    answer = []
    for num in numbers:
        bins = to_bin(num)
        res = btree(bins, 0, len(bins)-1)
        answer.append(1 if res else 0)
    return answer

def to_bin(num):
    bins = bin(num)[2:]
    total = 1; exp = 1
    while total < len(bins):
        total += pow(2, exp)
        exp += 1
    return '0' * (total - len(bins)) + bins

def btree(bins, left, right):
    # 리프 노드
    if left == right:
        return True

    # 루트 노드
    cur = (left + right) // 2

    if bins[cur] == '0':
        left_all_zero = all(b == '0' for b in bins[left : cur])
        right_all_zero = all(b == '0' for b in bins[cur+1: right+1])
        return left_all_zero and right_all_zero

    return btree(bins, left, cur-1) and btree(bins, cur+1, right)
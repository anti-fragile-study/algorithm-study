def solution(numbers):
    answer = []
    
    # 최대 2진수 길이 = 54
    lens_of_tree = [1]
    for idx in range(1, 6):
        lens_of_tree.append(lens_of_tree[-1] + 2 ** idx)
        
    for number in numbers:
        tree = bin(number)[2:]

        if len(tree) == 1:
            answer.append(int(tree[0] == '1'))
            continue
        
        ## 트리가 포화 트리가 되도록 문자열 조정
        while len(tree) not in lens_of_tree:
            tree = '0' + tree
        
        answer.append(int(check_tree(tree)))
        
    return answer

def check_tree(tree):
    if not tree:
        return True
        
    root_idx = len(tree) // 2
    
    left = count_children(tree[:root_idx])
    right = count_children(tree[root_idx + 1:])
    
    if tree[root_idx] == '0' and (left + right) > 0:
        # 루트는 없는데 자식이 있으면 부적절 => 탐색 중단
        return False
    else:
        # 서브 트리까지 적절한 지 탐색
        return check_tree(tree[:root_idx]) and check_tree(tree[root_idx + 1:])
    
    
def count_children(tree):
    if not tree:
        return 0
    
    root_idx = len(tree) // 2
    root_count = int(tree[root_idx])
    
    return count_children(tree[:root_idx]) + count_children(tree[root_idx + 1:]) + root_count

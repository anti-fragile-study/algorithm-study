from itertools import combinations, product

def solution(dices):
    N = len(dices)
    dice_nums = set(range(N))
    
    a_max_win = 0
    answer = []
    
    for a_dice_nums in combinations(dice_nums, N // 2):
        b_dice_nums = dice_nums - set(a_dice_nums)
        b_dice_nums = tuple(b_dice_nums)
        
        A = []
        for p in product(range(6), repeat = N//2):
            a_sum = 0
            for num, idx in enumerate(p):
                a_sum += dices[a_dice_nums[num]][idx]
            A.append(a_sum)
            
        B = []
        for p in product(range(6), repeat = N//2):
            b_sum = 0
            for num, idx in enumerate(p):
                b_sum += dices[b_dice_nums[num]][idx]
            B.append(b_sum)

        A.sort()
        B.sort()
        b_idx = 0
        
        a_win = 0
        for a in A:
            while b_idx < len(B) and B[b_idx] < a:
                b_idx += 1
            a_win += b_idx
            
        if a_win > a_max_win:
            a_max_win = a_win
            answer = a_dice_nums
    
    answer = [num + 1 for num in answer]
    return sorted(answer)

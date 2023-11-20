def solution(queue1, queue2):
    answer = 0
    sum_q1 = sum(queue1)
    sum_q2 = sum(queue2)
    mean_sum = (sum_q1 + sum_q2) / 2
    if mean_sum != int(mean_sum):
        return -1
    
    i = 0
    j = 0
    total_len = len(queue1) * 2
    # while i < len(queue1) and j < len(queue2) and sum_q1 != sum_q2:
    while i < total_len and j < total_len and sum_q1 != sum_q2:
        if sum_q1 > mean_sum:
            sum_q1 -= queue1[i]
            sum_q2 += queue1[i]
            queue2.append(queue1[i])
            i += 1

        else:# sum_q2 > mean_sum:
            sum_q2 -= queue2[j]
            sum_q1 += queue2[j]
            queue1.append(queue2[j])
            j += 1
    
    return -1 if sum_q1 != sum_q2 else i + j

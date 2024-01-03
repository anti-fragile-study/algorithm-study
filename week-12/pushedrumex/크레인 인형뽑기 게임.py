def solution(board, moves):
    answer = 0
    bucket = []
    stacks = [[] for _ in range(len(board[0]))]
    for row in board[::-1]:
        for i in range(len(row)):
            if row[i] != 0:
                stacks[i].append(row[i])
    for move in moves:
        move -= 1
        if len(stacks[move]) == 0: continue
        temp = stacks[move].pop()
        if len(bucket) > 0 and bucket[-1] == temp:
            bucket.pop()
            answer += 2
        else:
            bucket.append(temp)
            
    return answer
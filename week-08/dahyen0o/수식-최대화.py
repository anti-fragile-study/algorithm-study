from itertools import permutations

def solution(expression):
    answer = 0
    for ops in permutations(['+', '-', '*'], 3):
        answer = max(answer, abs(calculate(expression, ops, 0)))        
            
    return answer

def calculate(expression, ops, ops_idx):
    if ops_idx == len(ops) - 1:
        return eval(expression)
    
    expression = expression.split(ops[ops_idx])
    return eval(ops[ops_idx].join(list(map(lambda x: str(calculate(x, ops, ops_idx + 1)), expression))))

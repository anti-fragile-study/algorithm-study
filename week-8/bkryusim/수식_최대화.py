from itertools import permutations


def solution(expression):
    answer = 0
    operations = ("+", "-", "*", "/")
    list_exp = []

    last = 0
    for i, e in enumerate(expression):
        if e in operations:
            list_exp += [int(expression[last:i])] + [e]
            last = i + 1
    list_exp += [int(expression[last:])]
    cases = permutations(operations)
    p = False

    for case in cases:
        prev = list_exp[:]
        for operation in case:
            memo = []
            for i, c in enumerate(prev):
                if p:
                    p = False
                    continue
                if c == operation:
                    memo[-1] = eval(str(memo[-1]) + c + str(prev[i + 1]))
                    p = True
                else:
                    memo.append(c)
            prev = memo[:]
        if abs(memo[0]) > answer:
            answer = abs(memo[0])

    return answer

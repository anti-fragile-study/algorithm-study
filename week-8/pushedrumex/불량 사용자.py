from itertools import permutations

def solution(user_id, banned_id):
    answer = set()
    for user_ids in permutations(user_id, len(user_id)):
        temp = []
        visited = [False] * len(banned_id)
        for name in user_ids:
            for i in range(len(banned_id)):
                if isSame(banned_id[i], name) and not visited[i]:
                    temp.append(name)
                    visited[i] = True
                    break
        if len(temp) == len(banned_id):
            answer.add(tuple(sorted(temp)))
    return len(answer)

def isSame(pattern, name):
    if len(pattern) != len(name):
        return False
    for i in range(len(pattern)):
        if pattern[i] == "*": continue
        if pattern[i] != name[i]: return False
    
    return True
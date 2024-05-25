def solution(skill, skill_trees):
    pre_skill = {}
    for i in range(1, len(skill)):
        pre_skill[skill[i]] = skill[i-1]

    answer = len(skill_trees)
    for skill_tree in skill_trees:
        visited = {}
        for tree in skill_tree:
            if tree in skill[1:] and pre_skill[tree] not in visited:
                answer -= 1
                break
            visited[tree] = True
    return answer
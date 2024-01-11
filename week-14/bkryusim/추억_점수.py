def solution(name, yearning, photo):
    d = {name[i]: yearning[i] for i in range(len(yearning))}
    return list(map(lambda x: sum(map(lambda y: d.get(y, 0), x)), photo))

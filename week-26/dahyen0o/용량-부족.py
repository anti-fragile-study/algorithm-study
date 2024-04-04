T = int(input())

for _ in range(T):
    delete = set()
    names = set()
    DELETE = int(input())
    for _ in range(DELETE):
        name = input()
        names.add(name)
        for end in range(1, len(name) + 1):
            delete.add(name[:end])
    
    NOT_DELETE = int(input())
    for _ in range(NOT_DELETE):
        name = input()
        for end in range(1, len(name) + 1):
            if name[:end] in delete:
                delete.remove(name[:end])

    if NOT_DELETE == 0:
        print(1)
        continue

    # delete 에서 겹치는 단어들(ex. a*, aa*) 하나로 최적화
    d = set()
    for name in delete:
        for end in range(1, len(name)):
            if name[:end] in delete:
                d.add(name)
                break
    delete -= d

    # names 에서 delete 로 삭제 가능한 단어들 제외
    n = set()
    for name in names:
        for end in range(1, len(name) + 1):
            if name[:end] in delete:
                n.add(name)
                break
    names -= n
    
    print(len(names) + len(delete))

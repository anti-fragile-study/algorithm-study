import re

def split_head(file):
    for idx, val in enumerate(file):
        if val.isnumeric():
            return file[:idx], file[idx:]
    raise Exception("NO HEAD")
    
def split_number(file):
    for idx, val in enumerate(file):
        if not val.isnumeric():
            return file[:idx], file[idx:]
    return file, ''

def compare_name(name):
    return (name[0].lower(), int(name[1]))
    
def solution(files):
    # HEAD, NUMBER, TAIL 분리
    names = []
    for file in files:
        head, file = split_head(file)
        number, tail = split_number(file)
        names.append((head, number, tail))
        
    # 정렬
    names.sort(key=compare_name)
        
    return [''.join(name) for name in names]

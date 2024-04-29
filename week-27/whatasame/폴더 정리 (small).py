# 각 쿼리에 대하여 발견한 파일의 종류와 개수를 반환

# 폴더 이름은 고유

import sys
from collections import defaultdict

input = lambda:sys.stdin.readline().strip()

n, m = map(int, input().split())
data = [input().split() for _ in range(n + m)]
q = int(input())
queries = [input() for _ in range(q)]

def solution(data, queries):
    # 디렉토리 정보와 파일 정보 초기화
    directories, files = defaultdict(list), defaultdict(list)
    for parent, name, is_directory in data:
        if is_directory == "1":
            directories[parent].append(name)
        else:
            files[parent].append(name)

    # 쿼리 수행
    answer = []
    for query in queries:
        name = query.split("/")[-1]
        result = process(directories, files, name)
        answer.append((len(set(result)), len(result)))

    return answer

def process(directories, files, name):
    # 현재 디렉토리의 파일
    result = files[name][::]

    # 하위 디렉토리의 파일
    for child in directories[name]:
        result += process(directories, files, child)

    return result

for answer in solution(data, queries):
    print(" ".join(map(str, answer)))

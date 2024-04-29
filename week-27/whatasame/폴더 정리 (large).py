# 쿼리 대상 폴더 하위의 파일 종류 수와 개수를 반환

"""
4 4
main FolderZ 1
FolderZ File2 0
FolderX FolderC 1
FolderX File1 0
FolderC File2 0
FolderC File3 0
FolderZ FolderX 1
FolderX FolderE 1
2
main/FolderX main/FolderZ
main/FolderZ/FolderC main/FolderZ
2
main
main/FolderZ
"""

import sys
from collections import defaultdict

input = lambda: sys.stdin.readline().strip()

n, m = map(int, input().split())
info = [tuple(input().split()) for _ in range(n + m)]
k = int(input())
commands = [tuple(input().split()) for _ in range(k)]
q = int(input())
queries = [input() for _ in range(q)]

def solution(info, commands, queries):
    # 폴더 데이터 초기화
    folders = defaultdict(dict)
    for p, q, f in info:
        folders[p][q] = f

    # 폴더 병합
    for victim, target in commands:
        victim, target = victim.split("/")[-1], target.split("/")[-1]
        folders[target].update(folders[victim])
        folders.pop(victim)

    # 쿼리 실행
    answer = []
    for query in queries:
        target = query.split("/")[-1]
        result = examine(folders, target)
        answer.append((len(set(result)), len(result)))

    return answer

def examine(folders, target):
    files = []
    for q, f in folders[target].items():
        if f == "1":
            files.extend(examine(folders, q))
        else:
            files.append(q)

    return files

for answer in solution(info, commands, queries):
    print(*answer)

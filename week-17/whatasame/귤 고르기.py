# 서로 다른 종류의 수를 최소화

# k개를 고를 때 최소 종류 수를 반환

from collections import *

def solution(k, tangerine):
    # 크기별 개수를 센다
    conters = sorted([val for val in Counter(tangerine).values()], reverse = True)

    # 가장 많은 순서대로 센다.
    for idx, cnt in enumerate(conters):
        k -= cnt

        if k <= 0:
            return idx + 1

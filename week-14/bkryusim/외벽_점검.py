from itertools import permutations
from collections import deque


def solution(n, weak, dist):
    answer = 9

    for case in permutations(dist):
        queue = deque(weak)
        for _ in range(len(weak)):
            coverage = 0
            for friend_index, friend in enumerate(case):
                for weak_index, weak_point in enumerate(list(queue)[coverage:]):
                    braked = False
                    if weak_point - queue[coverage] > friend:
                        braked = True
                        break
                coverage += weak_index
                if coverage == len(weak) - 1 and not braked:
                    answer = min(answer, friend_index + 1)
                    break
            queue.append(queue.popleft() + n)

    return answer if answer != 9 else -1

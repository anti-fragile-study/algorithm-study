from collections import defaultdict


def solution(id_list, report, k):
    reported = defaultdict(set)

    for log in report:
        plaintiff, defendant = log.split()
        reported[defendant].add(plaintiff)

    mailed = defaultdict(int)

    for user, plaintiffs in reported.items():
        if len(plaintiffs) >= k:
            for plaintiff in plaintiffs:
                mailed[plaintiff] += 1

    return list(map(lambda x: mailed[x], id_list))

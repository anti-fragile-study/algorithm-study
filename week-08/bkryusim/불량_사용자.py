from itertools import permutations


def solution(user_id, banned_id):
    answer = set()

    for users in permutations(user_id, len(banned_id)):
        match = True

        for i, user in enumerate(users):
            if len(user) != len(banned_id[i]):
                match = False
                break

            for c in range(len(user)):
                if banned_id[i][c] == '*':
                    continue
                if banned_id[i][c] != users[i][c]:
                    match = False
                    break

            if not match:
                break

        if match:
            if frozenset(users) not in answer:
                answer.add(frozenset(users))

    return len(answer)

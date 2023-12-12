# 프로그래머스 이모티콘 할인행사
def solution(users, emoticons):
    return sale(users, emoticons, [])

def sale(users, emoticons, rates):
    if len(rates) == len(emoticons):
        return count(users, emoticons, rates)

    value = (0, 0)

    # 이모티콘 할인 판매
    for rate in [10, 20, 30, 40]:
        rates.append(rate)
        value = max(value, sale(users, emoticons, rates))
        rates.pop()

    return value

def count(users, emoticons, results):
    count = 0; profit = 0

    for urate, uprice in users:
        total = 0
        for i in range(len(results)):
            if results[i] >= urate:
                total += discount(results[i], emoticons[i])

        # 이모티콘 플러스 가입
        if total >= uprice:
            count += 1
            continue

        # 이모티콘 구매
        profit += total

    return count, profit

def discount(rate, regular):
    rate = 100 - rate
    return regular * rate * 0.01
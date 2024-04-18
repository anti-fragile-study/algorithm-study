N = int(input())
costs = list(map(int, input().split()))
COST = int(input())

def min_cost(except_zero=False):
    global costs
    num, cost = len(costs) - 1, costs[-1]
    for n in range(len(costs) - 2, -1, -1):
        if except_zero and n == 0:
            break
        if costs[n] < cost:
            cost = costs[n]
            num = n
    return (num, cost)

def init(MIN_COST):
    global COST
    result = []
    if MIN_COST[0] == 0:
        next_min_cost = min_cost(except_zero=True)
        if next_min_cost[1] <= COST:
            COST -= next_min_cost[1]
            result.append(next_min_cost[0])

    result += [MIN_COST[0] for _ in range(COST // MIN_COST[1])]
    COST %= MIN_COST[1]
    return result

MIN_COST = min_cost()
result = init(MIN_COST)

sorted_costs = [(num, cost) for num, cost in enumerate(costs)]
sorted_costs.sort(key=lambda x: (-x[0], x[1]))

c = 0
for r in range(len(result)):
    while c < len(sorted_costs) and COST + costs[result[r]] - sorted_costs[c][1] < 0:
        c += 1
    if c == len(sorted_costs):
        break
    if sorted_costs[c][0] > result[r] and COST + costs[result[r]] - sorted_costs[c][1] >= 0:
        COST = COST + costs[result[r]] - sorted_costs[c][1]
        result[r] = sorted_costs[c][0]

print(int(''.join(map(str, result))))

from collections import defaultdict

def solution(nodeinfo):
    N = len(nodeinfo)
    y_dic = defaultdict(list)
    node_dic = {}
    for i, node in enumerate(nodeinfo):
        x, y = node
        y_dic[y].append(x)
        node_dic[(x, y)] = i+1
    
    tree = [[0, 0, 0] for _ in range(100_000 + 1)]
    ys = sorted(y_dic.keys())
    yN = len(ys)

    for i in range(yN-1, 0, -1):
        print(y_dic[ys[i]])
        print(y_dic[ys[i-1]])
        for parent in y_dic[ys[i]]:
            for child in y_dic[ys[i-1]]:
                if parent < tree[parent][0]:
                    if child < parent:
                        tree[parent][1] = child
                        tree[child][0] = parent
                    elif child < tree[parent][0]:
                        tree[parent][2] = child
                        tree[child][0] = parent
                else:
                    if child > parent:
                        tree[parent][2] = child
                        tree[child][0] = parent
                    elif child > tree[parent][0]:
                        tree[parent][1] = child
                        tree[child][0] = parent
    answer = [[]]
    return answer
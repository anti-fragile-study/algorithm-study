import sys
sys.setrecursionlimit(10**9)

graph = {}
nums = {}
answer = [[], []]
def solution(nodeinfo):
    N = len(nodeinfo)
    for i in range(N):
        nodeinfo[i] = tuple(nodeinfo[i])
        
    for i in range(N):
        nums[nodeinfo[i]] = i+1
        graph[nodeinfo[i]] = [None, None]
        
    nodeinfo.sort(key=lambda x: -x[1])
    root = nodeinfo[0]
    for i in range(1, N):
        make_tree(root, nodeinfo[i])
    
    preorder(root)
    postorder(root)
    
    return answer

def make_tree(parent, child):
    if child[0] < parent[0]:
        if graph[parent][0] == None:
            graph[parent][0] = child
        else:
            make_tree(graph[parent][0], child)
    elif parent[0] < child[0]:
        if graph[parent][1] == None:
            graph[parent][1] = child
        else:
            make_tree(graph[parent][1], child)

# Root -> L -> R
def preorder(node):
    left, right = graph[node]
    answer[0].append(nums[node])
    if left != None:
        preorder(left)
    if right != None:
        preorder(right)

# L -> R -> Root
def postorder(node):
    left, right = graph[node]
    if left != None:
        postorder(left)
    if right != None:
        postorder(right)

    answer[1].append(nums[node])
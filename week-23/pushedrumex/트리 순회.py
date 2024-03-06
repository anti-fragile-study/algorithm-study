N = int(input())
tree = {}
for _ in range(N):
    node, left, right = input().split()
    tree[node] = (left, right)

# 전위 순회: ROOT -> L -> R
def preorder(node):
    global answer
    answer += node
    left, right = tree[node]
    if left != ".":
        preorder(left)
    if right != ".":
        preorder(right)

# 중위 순회: L -> ROOT -> R
def inorder(node):
    global answer
    left, right = tree[node]
    if left != ".":
        inorder(left)
    answer += node
    if right != ".":
        inorder(right)
    
# 후위 순회: L -> R -> ROOT
def postorder(node):
    global answer
    left, right = tree[node]
    if left != ".":
        postorder(left)
    if right != ".":
        postorder(right)
    answer += node

answer = ""
preorder("A")
print(answer)

answer = ""
inorder("A")
print(answer)

answer = ""
postorder("A")
print(answer)
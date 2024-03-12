import sys

input = sys.stdin.readline

n = int(input())
graph = {}
for _ in range(n):
    node, left, right = input().split()
    graph[node] = (left, right)

def solution(graph):
    return [preorder("A"), inorder("A"), postorder("A")]

def preorder(node):
    if node == ".":
        return ""

    return node + preorder(graph[node][0]) + preorder(graph[node][1])

def inorder(node):
    if node == ".":
        return ""

    return inorder(graph[node][0]) + node + inorder(graph[node][1])

def postorder(node):
    if node == ".":
        return ""

    return postorder(graph[node][0]) + postorder(graph[node][1]) + node

print("\n".join(solution(graph)))


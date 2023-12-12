def solution(n, k, cmd):
    class Node:
        def __init__(self, prev, next, value):
            self.prev = prev
            self.next = next
            self.value = value

    answer = ['O'] * n
    stack = []
    head = Node(None, None, 0)

    prev = head
    for i in range(n):
        next = Node(prev, None, i)
        prev.next = next
        prev = next

    cursor = head
    for _ in range(k+1):
        cursor = cursor.next

    for line in cmd:
        command = line[0]
        if command == 'U':
            X = int(line.split()[1])
            for _ in range(X):
                cursor = cursor.prev

        if command == 'D':
            X = int(line.split()[1])
            for _ in range(X):
                cursor = cursor.next

        if command == 'C':
            stack.append(cursor)
            answer[cursor.value] = 'X'
            if cursor.prev:
                cursor.prev.next = cursor.next
            if cursor.next:
                cursor.next.prev = cursor.prev

            if cursor.next:
                cursor = cursor.next
            else:
                cursor = cursor.prev

        if command == 'Z':
            node = stack.pop()
            if node.prev:
                node.prev.next = node
            if node.next:
                node.next.prev = node
            answer[node.value] = 'O'
    return ''.join(answer)

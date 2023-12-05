def solution(word):
    answer = [0]

    def dfs(s):
        if len(s) > 5:
            return False

        answer[0] += 1

        if s == word:
            return True

        for c in ['A', 'E', 'I', 'O', 'U']:
            res = dfs(s + c)
            if res:
                return True

    dfs("")

    return answer[0] - 1
